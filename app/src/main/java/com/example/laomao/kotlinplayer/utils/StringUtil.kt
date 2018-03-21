package com.example.laomao.kotlinplayer.utils

/**
 * User: laomao
 * Date: 2018-03-13
 * Time: 17-30
 *
 */
object StringUtil {

    val HOUR=60*60*1000
    val MIN=60*1000
    val SEC=1000

    /**
     * 格式化时间
     * 格式为：   00:10/10:10
     */
    fun formatTime(value:Int):String{
        var hour=value/ HOUR
        var min=value% HOUR/ MIN
        var sec=value% HOUR% MIN/ SEC

        var result=""

        if (hour==0){
            // 如果不到一小时则不显示小时
            result=String.format("%02d:%02d",min,sec)
        }else{
            result=String.format("%02d:%02d:%02d",hour,min,sec)
        }
        return result
    }

    val testHomeResult="{\n" +
            "    \"datas\": [\n" +
            "        {\n" +
            "            \"desc\": \"美女一\",\n" +
            "            \"image\": \"http://www.sucaifengbao.com/uploadfile/photo/meinvtupianbizhi/meinvtupianbizhi_813_004.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=be9014807fbe4a2486d6ac79aef8eb57&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女二\",\n" +
            "            \"image\": \"http://www.sucaifengbao.com/uploadfile/photo/meinvtupianbizhi/meinvtupianbizhi_813_074.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=9afffb682b5f42b09a06573053bdefbc&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女三\",\n" +
            "            \"image\": \"http://image5.tuku.cn/pic/wallpaper/meinv/qingtingyinyuedeerjimeinvbizhi/025.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=a6bd687b164a448b88c0f9b16f3e84ec&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女四\",\n" +
            "            \"image\": \"http://image5.tuku.cn/pic/wallpaper/meinv/qingtingyinyuedeerjimeinvbizhi/025.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=13250cef76964f35986c0e90a39d851e&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女五\",\n" +
            "            \"image\": \"http://www.sucaifengbao.com/uploadfile/photo/meinvtupianbizhi/meinvtupianbizhi_813_072.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=13250cef76964f35986c0e90a39d851e&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女六\",\n" +
            "            \"image\": \"http://file-www.sioe.cn/201203/10/2259566001.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=13250cef76964f35986c0e90a39d851e&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女七\",\n" +
            "            \"image\": \"http://img3.redocn.com/20100712/Redocn_2010071020004131.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=13250cef76964f35986c0e90a39d851e&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女八\",\n" +
            "            \"image\": \"http://img3.redocn.com/20111105/Redocn_2011103014253957.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=13250cef76964f35986c0e90a39d851e&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女九\",\n" +
            "            \"image\": \"http://www.popopic.com/wp-content/uploads/edd/2017/12/Beautiful-woman-and-sunshine-4034s.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=13250cef76964f35986c0e90a39d851e&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女十\",\n" +
            "            \"image\": \"http://img.sytuku.com/uptu/yulantu/160513/5-160513204105.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=13250cef76964f35986c0e90a39d851e&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"desc\": \"美女十一\",\n" +
            "            \"image\": \"http://file06.16sucai.com/2016/0524/a1e2c1e160c80becfa5d5d31ff6c34a0.jpg\",\n" +
            "            \"title\": \"爱你一万年\",\n" +
            "            \"url\": \"http://ic.snssdk.com/neihan/video/playback/?video_id=13250cef76964f35986c0e90a39d851e&quality=480p&line=0&is_gif=0.mp4\"\n" +
            "        }\n" +
            "    ]\n" +
            "}"

    val testTabResult="{\n" +
            "    \"tab\": [\n" +
            "        {\n" +
            "            \"name\": \"欧美\",\n" +
            "            \"id\": \"1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"日本\",\n" +
            "            \"id\": \"2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"韩国\",\n" +
            "            \"id\": \"3\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"内陆\",\n" +
            "            \"id\": \"4\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"港澳台\",\n" +
            "            \"id\": \"5\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"印度\",\n" +
            "            \"id\": \"6\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"俄罗斯\",\n" +
            "            \"id\": \"7\"\n" +
            "        }\n" +
            "    ]\n" +
            "}"
}