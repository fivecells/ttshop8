package com.qf.ttshop.lucene;

/**
 * 本案例中讲解的是Lucene索引库的增删改查
 *
 * User: DHC
 * Date: 2018/1/22
 * Time: 15:14
 * Version:V1.0
 */
/*public class IndexTest {

    @Test
    public void testCreateIndex() throws IOException {
        //采集原数据
        IBookDao bookDao = new BookDaoImpl();
        List<Book> bookList = bookDao.listBooks();
        //文档域 （文档域中存放的是一个个的文档document,一个document相当于表中一条记录）
        List<Document> documentList = new ArrayList<Document>();
        //将原数据中的Book对象转换Document对象
        for (Book book : bookList){
            Document document = new Document();
            //要不要分词：要不要拿这个字段搜索
            //要不要索引：要不要拿这个字段排序
            //要不要存储： 这个字段是否要存到document中
            //图书ID：不分词、要索引、要存储
            Field id = new StringField("id", book.getId().toString(), Field.Store.YES);
            //图书名称：要分词、要索引、要存储
            Field name = new TextField("name", book.getName(), Field.Store.YES);
            //图书价格：要分词、要索引、要存储
            Field price = new FloatField("price",book.getPrice(), Field.Store.YES);
            //图书图片地址：不分词、不索引、要存储
            Field pic = new StoredField("pic", book.getPic());
            //图片描述：要分词、要索引、要存储
            Field description = new TextField("description",book.getDescription(),Field.Store.YES);
            //将每一个Field添加文档中
            document.add(id);
            document.add(name);
            document.add(price);
            document.add(pic);
            document.add(description);
            //将每个document添加到文档域中
            documentList.add(document);
        }

        //可以使用中文分词器，也可以使用默认的分词器，默认的分词器对中文支持不好
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
        //指定索引库的地址
        File file = new File("d:/bookindex");
        Directory directory = FSDirectory.open(file);
        IndexWriter writer = new IndexWriter(directory,config);
        //遍历插入
        for (Document document:documentList){
            writer.addDocument(document);
        }
        //释放资源
        writer.close();
    }

    @Test
    public void testSearchIndex() throws ParseException, IOException {
        //QueryParser-->Query
        //File-->Directory-->IndexReader-->IndexSearcher-->TopDocs search(query,10)-->topDocs.totalHits
        //description就是要查询的字段
        QueryParser queryParser = new QueryParser("name",new StandardAnalyzer());
        Query query = queryParser.parse("name:solr");
        //指定索引库的本地文件
        File file = new File("d:/bookindex");
        Directory directory = FSDirectory.open(file);
        //获取索引读取器
        IndexReader reader = DirectoryReader.open(directory);
        //获取索引查询器
        IndexSearcher searcher = new IndexSearcher(reader);
        //进行搜索
        TopDocs topDocs = searcher.search(query, 10);
        //打印出匹配的条数
        System.out.println(topDocs.totalHits);

        //ScoreDoc[] scoreDocs = topDocs.scoreDocs;

    }

    @Test
    public void testDeleteIndex() throws IOException {
        //创建分词器
        Analyzer analyzer = new StandardAnalyzer();
        //创建IndexWriterConfig
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //指定索引库地址
        File file = new File("d:/bookindex");
        Directory directory = FSDirectory.open(file);
        IndexWriter writer = new IndexWriter(directory, config);
        Term term = new Term("name","solr");
        writer.deleteDocuments(term);
        //删除所有
        //这里的删除和直接删除物理文件不一样
        //deleteAll删除完后进行查询得到的结果为0
        //直接删除物理文件，进行查询会抛出异常
//        writer.deleteAll();
        writer.close();

    }


    @Test
    public void testUpdateIndex() throws IOException {
        //创建分词器
        Analyzer analyzer = new StandardAnalyzer();
        //创建IndexWriterConfig
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //指定索引库地址
        File file = new File("d:/bookindex");
        Directory directory = FSDirectory.open(file);
        IndexWriter writer = new IndexWriter(directory, config);

        //明确更新的字段
        Term term = new Term("name","solr");
        Document document = new Document();
        document.add(new StringField("name","java",Field.Store.YES));
        //更新
        //如果不存在这条记录就是新增，否则就是更新
        writer.updateDocument(term,document);
        writer.close();
    }


}*/
