package com.baizhi.dao;
import com.baizhi.entity.Product;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/10 0010.
 */
@Component
public class ProductDaoImpl{
        private int i=1;

        public List<Product> queryByTerm(String queryParam) {//传入要查询的参数 名词
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        List<Product> products = new ArrayList<>();

        try {
            int pageSize=5;//每页展示条数只要由前台传进来就好
            int pageNum=1;//当前第几页
            //MultiFieldQueryParser 分词器特点: 多列查询,可以基于一句话做索引,
            String[] fileds={"name","desc","price","status"};
            MultiFieldQueryParser multiFieldQueryParser=new MultiFieldQueryParser(Version.LUCENE_44,fileds,new IKAnalyzer());
            Query parse=null;
            try {
                parse = multiFieldQueryParser.parse(queryParam);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //高亮部分
            Formatter formatter=new SimpleHTMLFormatter("<font color='red'>","</font>");
            Scorer score=new QueryScorer(parse);
            Highlighter highlighter = new Highlighter(formatter,score);
            TopDocs topDocs = indexSearcher.search(parse, pageSize*pageNum);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = (pageNum-1)*pageSize; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                //拿到每篇文章的分数
                float score1 = scoreDoc.score;
                //System.out.println(score+"**********************");
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Product product = getProFormDoc(document,highlighter);//把高亮器给 getProFormDoc转化器
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void saveIndex(Product product) {
        product.setId(String.valueOf(i=i+1));
        System.out.println(i);
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document document = getDocFormPro(product);
        try {
            indexWriter.addDocument(document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }


    public void deleteIndex(String id) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term("id", id));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }
    }

    public void updateIndex(Product product) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFormPro = getDocFormPro(product);
        try {
            indexWriter.updateDocument(new Term("id", product.getId()), docFormPro);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            LuceneUtil.rollback(indexWriter);
            e.printStackTrace();
        }


    }

    public Product getProFormDoc(Document document,Highlighter highlighter) {
        Product product = new Product();
        product.setId(document.get("id"));
        product.setPrice(Double.parseDouble(document.get("price")));
        product.setImg(document.get("img"));
        product.setStatus(Integer.parseInt(document.get("status")));
        try {
            String bestFragment = highlighter.getBestFragment(new IKAnalyzer(), "title", document.get("desc"));
            if(bestFragment==null){
                product.setDesc(document.get("desc")); //对有可能出现搜索关键词的字段 设置高亮 需要判断是否为空
            }else {
                product.setDesc(bestFragment);
            }
            String bestFragment1 = highlighter.getBestFragment(new IKAnalyzer(), "title", document.get("name"));
            if(bestFragment1==null){
                product.setName(document.get("name"));
            }else{
                product.setName(bestFragment1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Document getDocFormPro(Product product) {
        Document document = new Document();
        document.add(new TextField("id", product.getId(), Field.Store.YES));
        document.add(new TextField("name", product.getName(), Field.Store.YES));
        TextField desc = new TextField("desc", product.getDesc(), Field.Store.YES);
        desc.setBoost(10F);//添加的时候可以给某个列加权
        document.add(desc);
        document.add(new DoubleField("price", product.getPrice(), Field.Store.YES));
        document.add(new TextField("img", product.getImg(), Field.Store.YES));
        document.add(new IntField("status", product.getStatus(), Field.Store.YES));

        return document;
    }
}
