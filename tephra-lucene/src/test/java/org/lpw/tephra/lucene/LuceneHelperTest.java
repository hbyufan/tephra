package org.lpw.tephra.lucene;

import org.junit.Test;
import org.lpw.tephra.test.CoreTestSupport;
import org.lpw.tephra.util.Generator;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lpw
 */
public class LuceneHelperTest extends CoreTestSupport {
    @Inject
    private Generator generator;
    @Inject
    private LuceneHelper luceneHelper;

    @Test
    public void query() {
        long time = System.currentTimeMillis();
        String[] strings = {"Lucene是apache软件基金会jakarta项目组的一个子项目", "是一个开放源代码的全文检索引擎工具包",
                "但它不是一个完整的全文检索引擎", "而是一个全文检索引擎的架构", "提供了完整的查询引擎和索引引擎", "部分文本分析引擎（英文与德文两种西方语言）",
                "Lucene的目的是为软件开发人员提供一个简单易用的工具包", "以方便的在目标系统中实现全文检索的功能", "或者是以此为基础建立起完整的全文检索引擎",
                "Lucene是一套用于全文检索和搜寻的开源程式库", "由Apache软件基金会支持和提供", "Lucene提供了一个简单却强大的应用程式接口",
                "能够做全文索引和搜寻", "在Java开发环境里Lucene是一个成熟的免费开源工具", "就其本身而言",
                "Lucene是当前以及最近几年最受欢迎的免费Java信息检索程序库", "人们经常提到信息检索程序库，虽然与搜索引擎有关",
                "但不应该将信息检索程序库与搜索引擎相混淆"};
        luceneHelper.clear("test");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            luceneHelper.source("test", "id " + i, strings[i]);
            map.put("id " + i, strings[i]);
        }
        luceneHelper.index("test");
        Set<String> words = new HashSet<>();
        words.add("全文");
        words.add("开源");
//        words.add("Java");
        Set<String> ids = luceneHelper.query("test", words, 1024);
        System.out.println(ids.size());
        ids.forEach(id -> System.out.println(id + "<==>" + map.get(id)));
        System.out.println((System.currentTimeMillis() - time) / 1000.0D);
    }
}
