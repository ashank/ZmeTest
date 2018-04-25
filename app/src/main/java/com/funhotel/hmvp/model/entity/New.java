/*
 *   Copyright (C) 2018  ZME
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.funhotel.hmvp.model.entity;

import java.util.List;

/**
 * Description ：新闻实体类
 * Author：ZME
 * Create Time ：2018/4/1 22:47
 * Modify Time：2018/4/1 22:47
 * Version：1.0
 */
public class New {

  /**
   * stat : 1 data : [{"uniquekey":"1f40748bc0b857c5298bc2bc1e4899f0","title":"五保老人有一个共同的\u201c亲人\u201d
   * 社区好心大叔给 3 位五保老人养老送终","date":"2018-04-01 18:44","category":"社会","author_name":"合肥晚报","url":"http://mini.eastday.com/mobile/180401184431195.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180401/20180401184431_e330b854a55fead4870d949dcffa67e6_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180401/20180401184431_e330b854a55fead4870d949dcffa67e6_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180401/20180401184431_e330b854a55fead4870d949dcffa67e6_1_mwpm_03200403.jpg"},{"uniquekey":"b25c05b40adb59831637a70a75e0e54f","title":"清明，另一种视角感知生命与亲情的可贵","date":"2018-04-01
   * 18:43","category":"社会","author_name":"新民网","url":"http://mini.eastday.com/mobile/180401184354422.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180401/20180401184354_a3e216f671b39e6bc740be7f8e1aa41b_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20180401/20180401184354_a3e216f671b39e6bc740be7f8e1aa41b_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20180401/20180401184354_a3e216f671b39e6bc740be7f8e1aa41b_2_mwpm_03200403.jpg"},{"uniquekey":"69b81cbde84c5a0d7e3c5bc743f484a6","title":"比起星巴克咖啡致癌
   * 更可怕的是国外中文造谣媒体","date":"2018-04-01 18:41","category":"社会","author_name":"人民日报","url":"http://mini.eastday.com/mobile/180401184102534.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180401/20180401184102_847d9317f0428dac718d8eed4578ed68_1_mwpm_03200403.jpg"},{"uniquekey":"e80274823cccf7efabd11926c317ea2f","title":"处警神速！嫌疑人作案逃跑不到5分钟被警方擒获","date":"2018-04-01
   * 18:40","category":"社会","author_name":"南方网","url":"http://mini.eastday.com/mobile/180401184044443.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180401/20180401184044_1410f82c3f1c7888da6eb16c31973e72_1_mwpm_03200403.jpg"},{"uniquekey":"428939410d61e1a6c2ab8dbfc40a4fb8","title":"巡逻途中倒在警车里，33岁的他再也没醒来","date":"2018-04-01
   * 18:33","category":"社会","author_name":"扬子晚报网","url":"http://mini.eastday.com/mobile/180401183351550.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180401/20180401183351_e0934a1407ff4dfc89dd643923bf7979_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180401/20180401183351_e0934a1407ff4dfc89dd643923bf7979_1_mwpm_03200403.jpg"},{"uniquekey":"d4b267047fda773a79041202bdea5538","title":"杭州一女大学生借款1.4万
   * 1个月后却要还10万","date":"2018-04-01 18:26","category":"社会","author_name":"浙江新闻","url":"http://mini.eastday.com/mobile/180401182635775.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180401/20180401182635_cebf425bfe0290dae6193a27abb135a5_1_mwpm_03200403.jpg"},{"uniquekey":"bb5d41cb4a04b362c857f9328a3da429","title":"早知如此，何必当初！农村常见的交通工具，如今却被叫停整顿","date":"2018-04-01
   * 18:26","category":"社会","author_name":"农参谋","url":"http://mini.eastday.com/mobile/180401182612555.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180401/20180401182612_fe6db906d54068406c8e003ab6c8eb0c_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20180401/20180401182612_fe6db906d54068406c8e003ab6c8eb0c_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20180401/20180401182612_fe6db906d54068406c8e003ab6c8eb0c_1_mwpm_03200403.jpg"},{"uniquekey":"76ac187a57121ac3d57fa456952cb67d","title":"罕见猛犸象牙化石
   * 或形成于300万年","date":"2018-04-01 18:24","category":"社会","author_name":"艺术品交流","url":"http://mini.eastday.com/mobile/180401182400729.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180401/20180401182400_2b2071a2f4673eb6244c844a655292d7_6_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180401/20180401182400_2b2071a2f4673eb6244c844a655292d7_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180401/20180401182400_2b2071a2f4673eb6244c844a655292d7_3_mwpm_03200403.jpg"},{"uniquekey":"a0f535c77ccdcd7086cb384026853905","title":"昆明官渡区一仓库突发大火火灾现场浓烟滚滚","date":"2018-04-01
   * 18:23","category":"社会","author_name":"央视新闻app","url":"http://mini.eastday.com/mobile/180401182359628.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180401/20180401182359_0e2de10d3a7f00628d187c498412a952_1_mwpm_03200403.jpg"},{"uniquekey":"24f6b020921c3dfc716b2ddde853f4c8","title":"用人单位单方面调整员工岗位是否违法？","date":"2018-04-01
   * 18:23","category":"社会","author_name":"股权落地思维","url":"http://mini.eastday.com/mobile/180401182357873.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180401/20180401182357_04d075ca2157a6abc6bc92852d260e73_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20180401/20180401182357_04d075ca2157a6abc6bc92852d260e73_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20180401/20180401182357_04d075ca2157a6abc6bc92852d260e73_2_mwpm_03200403.jpg"},{"uniquekey":"7c17c04e9c796e271af993ccc841b664","title":"国内公用电话亭的出路何在？","date":"2018-04-01
   * 18:23","category":"社会","author_name":"改个昵称想半天","url":"http://mini.eastday.com/mobile/180401182350718.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180401/20180401182350_28670cb5f0589ac945f133a8974e1b0a_1_mwpm_03200403.jpg"},{"uniquekey":"db26500b322e682ef4c676ed03b5d615","title":"我国最有魅力的五个城市，成都稳居榜首","date":"2018-04-01
   * 18:23","category":"社会","author_name":"火如歌","url":"http://mini.eastday.com/mobile/180401182346076.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180401/20180401182346_0100b595882c58eaf1a275c6934e861f_4_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20180401/20180401182346_0100b595882c58eaf1a275c6934e861f_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20180401/20180401182346_0100b595882c58eaf1a275c6934e861f_1_mwpm_03200403.jpg"},{"uniquekey":"03b0c76babf89f099052d32b33e8b9fc","title":"昌江白水马水库一工地发生持械入室抢劫案，警方跨市县追捕！","date":"2018-04-01
   * 18:23","category":"社会","author_name":"直播海南","url":"http://mini.eastday.com/mobile/180401182345505.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180401/20180401182345_8e5590a0ee0ae8ff3871173241c51ffa_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20180401/20180401182345_8e5590a0ee0ae8ff3871173241c51ffa_2_mwpm_03200403.jpg"},{"uniquekey":"499c2b54c602f9ab151f6b10e7eef377","title":"图说北中医丨留住三月末的北中医","date":"2018-04-01
   * 18:22","category":"社会","author_name":"北京中医药大学","url":"http://mini.eastday.com/mobile/180401182240763.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180401/20180401182240_6042aae7f60165a3100198843a55b1de_7_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20180401/20180401182240_6042aae7f60165a3100198843a55b1de_6_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20180401/20180401182240_6042aae7f60165a3100198843a55b1de_3_mwpm_03200403.jpg"},{"uniquekey":"4129bf5a85e9ea431d205b3f8c928291","title":"女子高速弃车散步女子在高速路上穿越公路匝道之后步行朝收费站走","date":"2018-04-01
   * 18:22","category":"社会","author_name":"梦想起航梦想历程","url":"http://mini.eastday.com/mobile/180401182230292.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180401/20180401182230_3eb2fa723189ee7d1e59fa8818153802_1_mwpm_03200403.jpg"},{"uniquekey":"2a52e4125080ce5597c65404ea79ee33","title":"河南荥阳：高毒危化品罐车发生泄漏
   * 村民紧急撤离","date":"2018-04-01 18:21","category":"社会","author_name":"人民网","url":"http://mini.eastday.com/mobile/180401182126646.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180401/20180401182126_d2cd24dac84c1e929baa0d539e7e7abb_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20180401/20180401182126_d2cd24dac84c1e929baa0d539e7e7abb_2_mwpm_03200403.jpg"},{"uniquekey":"3d0a2a1cbcdadc105bf5fff6d21fa9e8","title":"男子肇事后为获赔偿，叫来妻子\u201c顶包\u201d，构成肇事逃逸被行政处罚","date":"2018-04-01
   * 18:21","category":"社会","author_name":"奇闻八道","url":"http://mini.eastday.com/mobile/180401182126274.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180401/20180401182126_59d138b5d7880ee2c060bbd747c37468_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20180401/20180401182126_59d138b5d7880ee2c060bbd747c37468_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20180401/20180401182126_59d138b5d7880ee2c060bbd747c37468_2_mwpm_03200403.jpg"},{"uniquekey":"06546b5c9fe71153d5e1d445df11e566","title":"澄清：10年前中双色球3000元，需去福彩中心兑奖？这是真的","date":"2018-04-01
   * 18:21","category":"社会","author_name":"大乐期侃","url":"http://mini.eastday.com/mobile/180401182115909.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180401/20180401182115_26523b82c0127f98b21fe606d9aabde8_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20180401/20180401182115_26523b82c0127f98b21fe606d9aabde8_6_mwpm_03200403.jpg"},{"uniquekey":"5ee86810dcb6304a2014a28e146d707d","title":"魔性旋律陪你尬舞到天亮
   * 360智能粉丝节炫酷单曲了解下！","date":"2018-04-01 18:20","category":"社会","author_name":"TechVoice科技之声","url":"http://mini.eastday.com/mobile/180401182036936.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180401/20180401182036_0a29cea49db679c9c85634893836051c_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20180401/20180401182036_0a29cea49db679c9c85634893836051c_8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20180401/20180401182036_0a29cea49db679c9c85634893836051c_7_mwpm_03200403.jpg"},{"uniquekey":"075f48ed6f055916f5a320d13201646b","title":"【市州】网红男子直播被打，原因竟然是这个！","date":"2018-04-01
   * 18:19","category":"社会","author_name":"法治贵州网","url":"http://mini.eastday.com/mobile/180401181935098.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180401/20180401181935_c28104bf631738dd3e3e6f842d9f8129_1_mwpm_03200403.jpg"},{"uniquekey":"3fdf8a77d862baa17e36ea1207bd439e","title":"昆明一空置仓库着火现场浓烟滚滚
   * 消防员已赶到","date":"2018-04-01 18:19","category":"社会","author_name":"人民网","url":"http://mini.eastday.com/mobile/180401181927575.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180401/20180401181927_e19ba2ce28d7fc019ed77b5a0364b61c_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180401/20180401181927_e19ba2ce28d7fc019ed77b5a0364b61c_9_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180401/20180401181927_e19ba2ce28d7fc019ed77b5a0364b61c_4_mwpm_03200403.jpg"},{"uniquekey":"6ef5f1d3de779619a5c8b0b916a958ff","title":"市民微信转账被限制
   * 实测：微信支付超限需验证动态二维码","date":"2018-04-01 18:19","category":"社会","author_name":"成都全搜索","url":"http://mini.eastday.com/mobile/180401181918697.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180401/20180401181918_214dbb12db9d4fd719e899019c1e3975_1_mwpm_03200403.jpg"},{"uniquekey":"65c0de1eeb975546c9017b63535dd955","title":"网上找工作需谨慎
   * 小心被这种骗局骗了 应届生面试要小心了","date":"2018-04-01 18:16","category":"社会","author_name":"网贷之道","url":"http://mini.eastday.com/mobile/180401181658747.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180401/20180401181658_3c39cd3a46aa6850b055ef7631f8f6ac_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180401/20180401181658_3c39cd3a46aa6850b055ef7631f8f6ac_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180401/20180401181658_3c39cd3a46aa6850b055ef7631f8f6ac_1_mwpm_03200403.jpg"},{"uniquekey":"2509b9bfeb8896239e2d2dacb9d964a3","title":"守住自己拥有的，努力争取自己想要的，就是投机风险","date":"2018-04-01
   * 18:16","category":"社会","author_name":"毓管理所","url":"http://mini.eastday.com/mobile/180401181651549.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180401/20180401181651_bd2770df6727b3f077796331508eae53_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20180401/20180401181651_bd2770df6727b3f077796331508eae53_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20180401/20180401181651_bd2770df6727b3f077796331508eae53_2_mwpm_03200403.jpg"},{"uniquekey":"93e1346a636f894e262bb856ba50d15c","title":"法律规定起诉离婚多久自动离婚才算有效？","date":"2018-04-01
   * 18:16","category":"社会","author_name":"问法在线","url":"http://mini.eastday.com/mobile/180401181647282.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180401/20180401181647_b11b6de5ae873a6e5fad5f3bc0773dc5_1_mwpm_03200403.jpg"},{"uniquekey":"37fe2854dd7dee3388dc0467efa7b558","title":"看吃瓜群众神评\u201c咏春哼哈二将\u201d，见民智已开至瓜熟蒂落！","date":"2018-04-01
   * 18:16","category":"社会","author_name":"瑜说还休","url":"http://mini.eastday.com/mobile/180401181626587.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180401/20180401181626_f0c8a3131e6390789a045cf17ca47a5a_10_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20180401/20180401181626_f0c8a3131e6390789a045cf17ca47a5a_7_mwpm_03200403.jpg","thumbnail_pic_s03":"http://08.imgmini.eastday.com/mobile/20180401/20180401181626_f0c8a3131e6390789a045cf17ca47a5a_2_mwpm_03200403.jpg"},{"uniquekey":"ad1c373defd5b628e113d63e9e68378f","title":"当初说是被重点培养，3个月后她却连转正都转不了！","date":"2018-04-01
   * 18:16","category":"社会","author_name":"拾瓶笙","url":"http://mini.eastday.com/mobile/180401181625944.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180401/20180401181625_252327e1da12155689490a7a714f556e_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20180401/20180401181625_252327e1da12155689490a7a714f556e_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20180401/20180401181625_252327e1da12155689490a7a714f556e_4_mwpm_03200403.jpg"},{"uniquekey":"c7e73a5b02347520e2b1f6aefaeb2a25","title":"凌晨十岁孩子走在郊区马路上，代驾司机好心将其劝回","date":"2018-04-01
   * 18:16","category":"社会","author_name":"楚天都市报","url":"http://mini.eastday.com/mobile/180401181621349.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180401/20180401181621_9c30386420b2ac9eac68767a429e8325_3_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180401/20180401181621_9c30386420b2ac9eac68767a429e8325_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180401/20180401181621_9c30386420b2ac9eac68767a429e8325_2_mwpm_03200403.jpg"},{"uniquekey":"4580fa7a8d3aa3ee457aeb450ea4cade","title":"只有半截身子的6旬老人，一个瘪气篮球和2个小板凳成了他的腿","date":"2018-04-01
   * 18:16","category":"社会","author_name":"博雅物语","url":"http://mini.eastday.com/mobile/180401181611309.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180401/20180401181611_a183b961bd57a719d02943d2bcccae47_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20180401/20180401181611_a183b961bd57a719d02943d2bcccae47_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20180401/20180401181611_a183b961bd57a719d02943d2bcccae47_7_mwpm_03200403.jpg"},{"uniquekey":"89c9b546937f30d2fb6e83949e9040b4","title":"2018中国赛艇大师赛武汉站开赛
   * 江苏赛艇俱乐部斩获多金","date":"2018-04-01 18:14","category":"社会","author_name":"荔枝网","url":"http://mini.eastday.com/mobile/180401181418469.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180401/20180401181418_855445a1e882a8a162c71540bca6f72f_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20180401/20180401181418_855445a1e882a8a162c71540bca6f72f_7_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20180401/20180401181418_855445a1e882a8a162c71540bca6f72f_5_mwpm_03200403.jpg"}]
   */
  private String stat;
  private List<DataEntity> data;

  public String getStat() {
    return stat;
  }

  public void setStat(String stat) {
    this.stat = stat;
  }

  public List<DataEntity> getData() {
    return data;
  }

  public void setData(List<DataEntity> data) {
    this.data = data;
  }

  public static class DataEntity {

    /**
     * uniquekey : 1f40748bc0b857c5298bc2bc1e4899f0 title : 五保老人有一个共同的“亲人” 社区好心大叔给 3 位五保老人养老送终 date
     * : 2018-04-01 18:44 category : 社会 author_name : 合肥晚报 url : http://mini.eastday.com/mobile/180401184431195.html
     * thumbnail_pic_s : http://00.imgmini.eastday.com/mobile/20180401/20180401184431_e330b854a55fead4870d949dcffa67e6_3_mwpm_03200403.jpg
     * thumbnail_pic_s02 : http://00.imgmini.eastday.com/mobile/20180401/20180401184431_e330b854a55fead4870d949dcffa67e6_2_mwpm_03200403.jpg
     * thumbnail_pic_s03 : http://00.imgmini.eastday.com/mobile/20180401/20180401184431_e330b854a55fead4870d949dcffa67e6_1_mwpm_03200403.jpg
     */

    private String uniquekey;
    private String title;
    private String date;
    private String category;
    private String author_name;
    private String url;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;

    public String getUniquekey() {
      return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
      this.uniquekey = uniquekey;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getCategory() {
      return category;
    }

    public void setCategory(String category) {
      this.category = category;
    }

    public String getAuthor_name() {
      return author_name;
    }

    public void setAuthor_name(String author_name) {
      this.author_name = author_name;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getThumbnail_pic_s() {
      return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
      this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
      return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
      this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
      return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
      this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    @Override
    public String toString() {
      return "DataEntity{" +
          "uniquekey='" + uniquekey + '\'' +
          ", title='" + title + '\'' +
          ", date='" + date + '\'' +
          ", category='" + category + '\'' +
          ", author_name='" + author_name + '\'' +
          ", url='" + url + '\'' +
          ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
          ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
          ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
          '}';
    }
  }

  @Override
  public String toString() {
    return "ResultEntity{" +
        "stat='" + stat + '\'' +
        ", data=" + data +
        '}';
  }
}

