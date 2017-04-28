//package hanlp;
//
//import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
//
///**
// * 语义距离
// * @author hankcs
// */
//public class DemoWordDistance
//{
//    public static void main(String[] args)
//    {
//        String[] wordArray = new String[]
//                {
//                        "香蕉",
//                        "苹果",
//                        "白菜",
//                        "水果",
//                        "蔬菜",
//                        "自行车",
//                        "公交车",
//                        "飞机",
//                        "买",
//                        "卖",
//                        "购入",
//                        "新年",
//                        "春节",
//                        "丢失",
//                        "补办",
//                        "办理",
//                        "送给",
//                        "寻找",
//                        "孩子",
//                        "教室",
//                        "教师",
//                        "会计",
//                };
//        for (String a : wordArray)
//        {
//            for (String b : wordArray)
//            {
//                System.out.println(a + "\t" + b + "\t之间的距离是\t" + CoreSynonymDictionary.distance(a, b));
//            }
//        }
//    }
//}