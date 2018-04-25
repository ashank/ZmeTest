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
 * Description ：NewEntity
 * Author：ZME
 * Create Time ：2018/4/2 18:00
 * Modify Time：2018/4/2 18:00
 * Version：1.0
 */
public class NewEntity {


  /**
   * reason : 成功的返回
   * result : {"stat":"1","data":[{"uniquekey":"cbccd75b53273a61d217aed4354997de","title":"广东轿车冲公交站致2死4伤现场","date":"2018-04-02 16:24","category":"头条","author_name":"凤凰网","url":"http://mini.eastday.com/mobile/180402162409811.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402162409_8f8a8c7743294544d505c3ec371559d0_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20180402/20180402162409_8f8a8c7743294544d505c3ec371559d0_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20180402/20180402162409_8f8a8c7743294544d505c3ec371559d0_3_mwpm_03200403.jpg"},{"uniquekey":"23e4cf11762b5d7dbc03f5da1e0fe2ad","title":"冬天穿一条裤子居然能瘦身还能美白？她是怎么做到的？","date":"2018-04-02 16:10","category":"头条","author_name":"蒙古味道","url":"http://mini.eastday.com/mobile/180402161025800.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180402/20180402161025_e35780ff681e7fb5a48dfca355141ff1_10_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180402/20180402161025_e35780ff681e7fb5a48dfca355141ff1_8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180402/20180402161025_e35780ff681e7fb5a48dfca355141ff1_17_mwpm_03200403.jpg"},{"uniquekey":"b8dd0448c9a78663a8d11fceaf05ca10","title":"国家监委挂牌后打下第一虎，通报有哪三处玄机","date":"2018-04-02 16:06","category":"头条","author_name":"解放网","url":"http://mini.eastday.com/mobile/180402160615595.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180402/20180402160615_229e150e94d62f3a17c3e687fd0f7a45_1_mwpm_03200403.jpg"},{"uniquekey":"6f2fe7f3e6d48d401d6987a2bf29b2df","title":"郁慕明：新党愿作服务者 帮助大陆台商更好发展","date":"2018-04-02 16:02","category":"头条","author_name":"中国台湾网","url":"http://mini.eastday.com/mobile/180402160236323.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402160236_7ffbe3be07b709f4994230c2ca83c9f6_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20180402/20180402160236_7ffbe3be07b709f4994230c2ca83c9f6_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20180402/20180402160236_7ffbe3be07b709f4994230c2ca83c9f6_4_mwpm_03200403.jpg"},{"uniquekey":"379182388cc14c3630da94bd93a77277","title":"诺贝尔奖得主维特里希等6人在沪拿到中国\u201c绿卡\u201d","date":"2018-04-02 15:56","category":"头条","author_name":"人民网本地站","url":"http://mini.eastday.com/mobile/180402155621837.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180402/20180402155621_20e6794818cde4916e28a4350c81cabd_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20180402/20180402155621_20e6794818cde4916e28a4350c81cabd_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20180402/20180402155621_20e6794818cde4916e28a4350c81cabd_6_mwpm_03200403.jpg"},{"uniquekey":"7e39c48ef0373e69aab84d468c2ef309","title":"原工商总局副局长王江平任工信部副部长(图/简历)","date":"2018-04-02 15:52","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/180402155224242.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402155224_b051fb30efbe196e1ebcfae5fcf2f89e_1_mwpm_03200403.jpg"},{"uniquekey":"438eb8bec414ed63a657adbca19c0ea5","title":"日本人狂杀333头鲸鱼血腥至极 骂声一片仍死不悔改","date":"2018-04-02 15:47","category":"头条","author_name":"东方头条","url":"http://mini.eastday.com/mobile/180402154756273.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180402/20180402_a10d48b1fa1f7da5cac36e11381069c2_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180402/20180402_f3ddc5d6c7f17bd3b5800a8e73dad015_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180402/20180402_5c0e38b8305fe1ca60a6c1862f7bc396_cover_mwpm_03200403.jpg"},{"uniquekey":"f9b57d239f0c485759fe335b371bdf7f","title":"看过廉政话剧的副省长深夜被拿下 曾称忠诚干净根植于心","date":"2018-04-02 15:43","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/180402154338772.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180402/20180402154338_284717045d25b8e02c728b8a34f92ab7_1_mwpm_03200403.jpg"},{"uniquekey":"6550c53b202e6c08c9bc46b48371ef02","title":"福建省第27个全国税收宣传月4月2日启动","date":"2018-04-02 15:42","category":"头条","author_name":"国际在线本地站","url":"http://mini.eastday.com/mobile/180402154241611.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402154241_e1c554f6b4a7fddaeca6bfe5ec11de48_1_mwpm_03200403.jpg"},{"uniquekey":"15adc6ae55af604d076cb5df2dff22d0","title":"官方为何再提IPO审核腐败问题？证监会今年有何新计划","date":"2018-04-02 15:42","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/180402154216836.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180402/20180402154216_99409c80d5f5cf301effc2c8bc3a3259_1_mwpm_03200403.jpg"},{"uniquekey":"26b4b003c1f5aa2bb08557c9c7f760d8","title":"跟中国和好后 这个国家的三文鱼对华出口量激增8倍","date":"2018-04-02 15:17","category":"头条","author_name":"观察者网","url":"http://mini.eastday.com/mobile/180402151738270.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180402/20180402151738_afb9b739c75e705ffae14c9e1d4849a8_1_mwpm_03200403.jpg"},{"uniquekey":"83865a6a9e8c701c2fcbec08e2c1e859","title":"斯琴毕力格任鄂尔多斯市委副书记 提名为市长候选人","date":"2018-04-02 15:17","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/180402151710040.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180402/20180402151710_17b1767d7b82d4c64523c1cadb5b1ca7_1_mwpm_03200403.jpg"},{"uniquekey":"de2f689245ede53bfb0fb10d67e5e015","title":"梁蓉兰任甘肃省委组织部副部长、省委老干部局局长","date":"2018-04-02 15:17","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/180402151709919.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180402/20180402151709_a93034755b56d92d34c154635ff41c0f_1_mwpm_03200403.jpg"},{"uniquekey":"5e2766578187b16e8a4a238facf1faa0","title":"是中国技术给我们带来了丰收","date":"2018-04-02 15:07","category":"头条","author_name":"驻马拉维使馆","url":"http://mini.eastday.com/mobile/180402150717143.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402150717_a297a316fb052a5b2c7962ed219e9dec_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20180402/20180402150717_a297a316fb052a5b2c7962ed219e9dec_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20180402/20180402150717_a297a316fb052a5b2c7962ed219e9dec_3_mwpm_03200403.jpg"},{"uniquekey":"a130357e5d7cfc098df04a8d8506ed7f","title":"政府工作报告：了解中国的最好窗口","date":"2018-04-02 15:06","category":"头条","author_name":"今日中国","url":"http://mini.eastday.com/mobile/180402150618990.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180402/20180402150618_015919bd35b1c786df774a5d815d3527_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20180402/20180402150618_015919bd35b1c786df774a5d815d3527_2_mwpm_03200403.jpg"},{"uniquekey":"95b817cb173111e7c74cf5e278c5a15d","title":"怎么避免\u201c摩擦\u201d尴尬？6式瑜伽打造圆润的蜜腿","date":"2018-04-02 15:01","category":"头条","author_name":"放眼看天下","url":"http://mini.eastday.com/mobile/180402150139471.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180402/20180402_f7460919ec59d1cca5c98b41cfbbb76d_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20180402/20180402_dc08885f8af4f7d4a0798aafd70f08ee_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20180402/20180402_3301890abcde7cbeb77a4f4873f11b62_mwpm_03200403.jpg"},{"uniquekey":"b75f6f4a15c32e677e68d3aeacb8d5fe","title":"驻柬埔寨大使熊波出席2018中国\u2014东盟博览会柬埔寨展开幕式","date":"2018-04-02 14:57","category":"头条","author_name":"驻柬埔寨使馆","url":"http://mini.eastday.com/mobile/180402145704207.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180402/20180402145704_7b933a60548491eace71217f235a8878_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20180402/20180402145704_7b933a60548491eace71217f235a8878_2_mwpm_03200403.jpg"},{"uniquekey":"271feee8ca2e36fdd0a1c2f8e01ac538","title":"克什米尔再起冲突，印度步步紧逼，巴铁将如何应对？","date":"2018-04-02 14:52","category":"头条","author_name":"四战四南","url":"http://mini.eastday.com/mobile/180402145239850.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180402/20180402_d6e51ed7c21a0201b23ae3dae06d2817_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180402/20180402_daf2cfb1bd710f51a2a3b5b4a6f14671_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180402/20180402_fba71539e2fee2b42c7591820c639f6b_cover_mwpm_03200403.jpg"},{"uniquekey":"d6441e01abd0692241252402f130e8c8","title":"不同寻常，中纪委国家监委为何\u201c深夜打虎\u201d","date":"2018-04-02 14:52","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/180402145211320.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180402/20180402145211_0112e1dcb6d8b7ea024ec551aa4ff3a6_1_mwpm_03200403.jpg"},{"uniquekey":"af182ad81a13ba34d5cbbea3fa18fd13","title":"三国死得最可惜的三大名将，实力可傲视三国，却都死于鼠辈之手！","date":"2018-04-02 14:47","category":"头条","author_name":"天源向上","url":"http://mini.eastday.com/mobile/180402144738161.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180402/20180402144738_043c4e914a78cd5925ba9c92c5355901_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180402/20180402144738_043c4e914a78cd5925ba9c92c5355901_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180402/20180402144738_043c4e914a78cd5925ba9c92c5355901_3_mwpm_03200403.jpg"},{"uniquekey":"018a14cca06285e9719965db4accc7bd","title":"被吓了？两月大女婴野生动物园游玩心脏骤停惨死","date":"2018-04-02 14:43","category":"头条","author_name":"看看新闻网","url":"http://mini.eastday.com/mobile/180402144345555.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20180402/20180402144345_d920d63dcc3a345f0fcc8be26056acf1_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20180402/20180402144345_d920d63dcc3a345f0fcc8be26056acf1_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20180402/20180402144345_d920d63dcc3a345f0fcc8be26056acf1_2_mwpm_03200403.jpg"},{"uniquekey":"b6390882e3f0c1027ea8abad3d18f665","title":"这欧盟大国突然发声拉了俄罗斯一把，英美对俄外交战该偃旗息鼓了","date":"2018-04-02 14:43","category":"头条","author_name":"高峰军事观察","url":"http://mini.eastday.com/mobile/180402144336517.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180402/20180402_8f624317df8a10b785257c9048800ad2_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20180402/20180402_9301c6926af305619d8b7cd550eaccad_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20180402/20180402_900683aaa8ff0c9afec5ab6fd092c83a_cover_mwpm_03200403.jpg"},{"uniquekey":"910de2fb22e6b47ec837da6ca4f762c0","title":"大陆惠台措施见效 台高中生申请大陆就学人数增加","date":"2018-04-02 14:41","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/180402144150274.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180402/20180402144150_ddcd6979271b6d157a563e264a1d0be8_1_mwpm_03200403.jpg"},{"uniquekey":"f7e92bfaf50fdab9a53ff0b546f2c628","title":"叙利亚击落以色列战机，是俄罗斯一手主导？此国才是背后真正推手","date":"2018-04-02 14:36","category":"头条","author_name":"猛禽","url":"http://mini.eastday.com/mobile/180402143638281.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180402/20180402_d0d443b1f3194c4d663bd38f557e9d95_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180402/20180402_b9444fbf68b77e3bdcaab305b2d475c3_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180402/20180402_1a06b8a832bb6b20de928e38c2e5e55c_cover_mwpm_03200403.jpg"},{"uniquekey":"1338c997a6c13aa92c0758e23c142976","title":"武侠小说有多坑传统武术？会不会吹和能不能打其实都是生意经！","date":"2018-04-02 14:36","category":"头条","author_name":"冷兵器研究所","url":"http://mini.eastday.com/mobile/180402143600004.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180402/20180402_58cdb29a60e038b137b87702c7e8b93c_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180402/20180402_cc21ceed96679245e49a029709d2ab08_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180402/20180402_cb786bfde9b93879a718b3d2560c5dcb_cover_mwpm_03200403.jpg"},{"uniquekey":"66cd39a7331fa876ace9b65fbe1785c7","title":"驻波黑大使陈波会见波黑议会民族院副主席乔拉克","date":"2018-04-02 14:25","category":"头条","author_name":"驻波黑使馆","url":"http://mini.eastday.com/mobile/180402142558121.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402142558_e11eda1f13659ca92818efa7ca1d87c0_1_mwpm_03200403.jpg"},{"uniquekey":"388b42df19b79f388cfa5a4bba0ddb52","title":"周海媚版灭绝师太现场花絮曝光，每个眼神都透露着霸气与狠毒","date":"2018-04-02 14:22","category":"头条","author_name":"千龙娱乐","url":"http://mini.eastday.com/mobile/180402142228541.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180402/20180402142228_20ced545c51138a2f56adcda53b219df_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20180402/20180402142228_20ced545c51138a2f56adcda53b219df_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20180402/20180402142228_20ced545c51138a2f56adcda53b219df_3_mwpm_03200403.jpg"},{"uniquekey":"873c68b0ea980b941e9fae05dcd56b00","title":"4月2日生猪价格继续上涨 我们应当理性看待本次猪价格上涨","date":"2018-04-02 14:22","category":"头条","author_name":"养殖通","url":"http://mini.eastday.com/mobile/180402142226274.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180402/20180402142226_a4968db92c67a0b3237f69d534c54162_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20180402/20180402142226_a4968db92c67a0b3237f69d534c54162_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20180402/20180402142226_a4968db92c67a0b3237f69d534c54162_3_mwpm_03200403.jpg"},{"uniquekey":"485a776dea1737bc49f4b6ce23bf4967","title":"守护亿亩良田 金正大集团CEO白瑛详解亲土种植\u201c百千亿行动\u201d","date":"2018-04-02 14:20","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/180402142025496.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180402/20180402142025_ac9d3b728c8ee8be8b4bab77856feb1f_1_mwpm_03200403.jpg"},{"uniquekey":"ed311696843f7aa6a719bde298ad9136","title":"2018\u201c一带一路\u201d与民营企业合作论坛在京举行","date":"2018-04-02 14:12","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/180402141256871.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180402/20180402141256_492d190b1261463cea3d437b25c122aa_1_mwpm_03200403.jpg"}]}
   * error_code : 0
   */

  private String reason;
  private ResultEntity result;
  private int error_code;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public ResultEntity getResult() {
    return result;
  }

  public void setResult(ResultEntity result) {
    this.result = result;
  }

  public int getError_code() {
    return error_code;
  }

  public void setError_code(int error_code) {
    this.error_code = error_code;
  }

  public static class ResultEntity {

    /**
     * stat : 1
     * data : [{"uniquekey":"cbccd75b53273a61d217aed4354997de","title":"广东轿车冲公交站致2死4伤现场","date":"2018-04-02 16:24","category":"头条","author_name":"凤凰网","url":"http://mini.eastday.com/mobile/180402162409811.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402162409_8f8a8c7743294544d505c3ec371559d0_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20180402/20180402162409_8f8a8c7743294544d505c3ec371559d0_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20180402/20180402162409_8f8a8c7743294544d505c3ec371559d0_3_mwpm_03200403.jpg"},{"uniquekey":"23e4cf11762b5d7dbc03f5da1e0fe2ad","title":"冬天穿一条裤子居然能瘦身还能美白？她是怎么做到的？","date":"2018-04-02 16:10","category":"头条","author_name":"蒙古味道","url":"http://mini.eastday.com/mobile/180402161025800.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180402/20180402161025_e35780ff681e7fb5a48dfca355141ff1_10_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180402/20180402161025_e35780ff681e7fb5a48dfca355141ff1_8_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180402/20180402161025_e35780ff681e7fb5a48dfca355141ff1_17_mwpm_03200403.jpg"},{"uniquekey":"b8dd0448c9a78663a8d11fceaf05ca10","title":"国家监委挂牌后打下第一虎，通报有哪三处玄机","date":"2018-04-02 16:06","category":"头条","author_name":"解放网","url":"http://mini.eastday.com/mobile/180402160615595.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180402/20180402160615_229e150e94d62f3a17c3e687fd0f7a45_1_mwpm_03200403.jpg"},{"uniquekey":"6f2fe7f3e6d48d401d6987a2bf29b2df","title":"郁慕明：新党愿作服务者 帮助大陆台商更好发展","date":"2018-04-02 16:02","category":"头条","author_name":"中国台湾网","url":"http://mini.eastday.com/mobile/180402160236323.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402160236_7ffbe3be07b709f4994230c2ca83c9f6_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20180402/20180402160236_7ffbe3be07b709f4994230c2ca83c9f6_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20180402/20180402160236_7ffbe3be07b709f4994230c2ca83c9f6_4_mwpm_03200403.jpg"},{"uniquekey":"379182388cc14c3630da94bd93a77277","title":"诺贝尔奖得主维特里希等6人在沪拿到中国\u201c绿卡\u201d","date":"2018-04-02 15:56","category":"头条","author_name":"人民网本地站","url":"http://mini.eastday.com/mobile/180402155621837.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180402/20180402155621_20e6794818cde4916e28a4350c81cabd_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20180402/20180402155621_20e6794818cde4916e28a4350c81cabd_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20180402/20180402155621_20e6794818cde4916e28a4350c81cabd_6_mwpm_03200403.jpg"},{"uniquekey":"7e39c48ef0373e69aab84d468c2ef309","title":"原工商总局副局长王江平任工信部副部长(图/简历)","date":"2018-04-02 15:52","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/180402155224242.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402155224_b051fb30efbe196e1ebcfae5fcf2f89e_1_mwpm_03200403.jpg"},{"uniquekey":"438eb8bec414ed63a657adbca19c0ea5","title":"日本人狂杀333头鲸鱼血腥至极 骂声一片仍死不悔改","date":"2018-04-02 15:47","category":"头条","author_name":"东方头条","url":"http://mini.eastday.com/mobile/180402154756273.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180402/20180402_a10d48b1fa1f7da5cac36e11381069c2_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180402/20180402_f3ddc5d6c7f17bd3b5800a8e73dad015_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180402/20180402_5c0e38b8305fe1ca60a6c1862f7bc396_cover_mwpm_03200403.jpg"},{"uniquekey":"f9b57d239f0c485759fe335b371bdf7f","title":"看过廉政话剧的副省长深夜被拿下 曾称忠诚干净根植于心","date":"2018-04-02 15:43","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/180402154338772.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180402/20180402154338_284717045d25b8e02c728b8a34f92ab7_1_mwpm_03200403.jpg"},{"uniquekey":"6550c53b202e6c08c9bc46b48371ef02","title":"福建省第27个全国税收宣传月4月2日启动","date":"2018-04-02 15:42","category":"头条","author_name":"国际在线本地站","url":"http://mini.eastday.com/mobile/180402154241611.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402154241_e1c554f6b4a7fddaeca6bfe5ec11de48_1_mwpm_03200403.jpg"},{"uniquekey":"15adc6ae55af604d076cb5df2dff22d0","title":"官方为何再提IPO审核腐败问题？证监会今年有何新计划","date":"2018-04-02 15:42","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/180402154216836.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180402/20180402154216_99409c80d5f5cf301effc2c8bc3a3259_1_mwpm_03200403.jpg"},{"uniquekey":"26b4b003c1f5aa2bb08557c9c7f760d8","title":"跟中国和好后 这个国家的三文鱼对华出口量激增8倍","date":"2018-04-02 15:17","category":"头条","author_name":"观察者网","url":"http://mini.eastday.com/mobile/180402151738270.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180402/20180402151738_afb9b739c75e705ffae14c9e1d4849a8_1_mwpm_03200403.jpg"},{"uniquekey":"83865a6a9e8c701c2fcbec08e2c1e859","title":"斯琴毕力格任鄂尔多斯市委副书记 提名为市长候选人","date":"2018-04-02 15:17","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/180402151710040.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180402/20180402151710_17b1767d7b82d4c64523c1cadb5b1ca7_1_mwpm_03200403.jpg"},{"uniquekey":"de2f689245ede53bfb0fb10d67e5e015","title":"梁蓉兰任甘肃省委组织部副部长、省委老干部局局长","date":"2018-04-02 15:17","category":"头条","author_name":"人民网","url":"http://mini.eastday.com/mobile/180402151709919.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180402/20180402151709_a93034755b56d92d34c154635ff41c0f_1_mwpm_03200403.jpg"},{"uniquekey":"5e2766578187b16e8a4a238facf1faa0","title":"是中国技术给我们带来了丰收","date":"2018-04-02 15:07","category":"头条","author_name":"驻马拉维使馆","url":"http://mini.eastday.com/mobile/180402150717143.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402150717_a297a316fb052a5b2c7962ed219e9dec_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20180402/20180402150717_a297a316fb052a5b2c7962ed219e9dec_4_mwpm_03200403.jpg","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20180402/20180402150717_a297a316fb052a5b2c7962ed219e9dec_3_mwpm_03200403.jpg"},{"uniquekey":"a130357e5d7cfc098df04a8d8506ed7f","title":"政府工作报告：了解中国的最好窗口","date":"2018-04-02 15:06","category":"头条","author_name":"今日中国","url":"http://mini.eastday.com/mobile/180402150618990.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180402/20180402150618_015919bd35b1c786df774a5d815d3527_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20180402/20180402150618_015919bd35b1c786df774a5d815d3527_2_mwpm_03200403.jpg"},{"uniquekey":"95b817cb173111e7c74cf5e278c5a15d","title":"怎么避免\u201c摩擦\u201d尴尬？6式瑜伽打造圆润的蜜腿","date":"2018-04-02 15:01","category":"头条","author_name":"放眼看天下","url":"http://mini.eastday.com/mobile/180402150139471.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180402/20180402_f7460919ec59d1cca5c98b41cfbbb76d_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20180402/20180402_dc08885f8af4f7d4a0798aafd70f08ee_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20180402/20180402_3301890abcde7cbeb77a4f4873f11b62_mwpm_03200403.jpg"},{"uniquekey":"b75f6f4a15c32e677e68d3aeacb8d5fe","title":"驻柬埔寨大使熊波出席2018中国\u2014东盟博览会柬埔寨展开幕式","date":"2018-04-02 14:57","category":"头条","author_name":"驻柬埔寨使馆","url":"http://mini.eastday.com/mobile/180402145704207.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180402/20180402145704_7b933a60548491eace71217f235a8878_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://08.imgmini.eastday.com/mobile/20180402/20180402145704_7b933a60548491eace71217f235a8878_2_mwpm_03200403.jpg"},{"uniquekey":"271feee8ca2e36fdd0a1c2f8e01ac538","title":"克什米尔再起冲突，印度步步紧逼，巴铁将如何应对？","date":"2018-04-02 14:52","category":"头条","author_name":"四战四南","url":"http://mini.eastday.com/mobile/180402145239850.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20180402/20180402_d6e51ed7c21a0201b23ae3dae06d2817_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://00.imgmini.eastday.com/mobile/20180402/20180402_daf2cfb1bd710f51a2a3b5b4a6f14671_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://00.imgmini.eastday.com/mobile/20180402/20180402_fba71539e2fee2b42c7591820c639f6b_cover_mwpm_03200403.jpg"},{"uniquekey":"d6441e01abd0692241252402f130e8c8","title":"不同寻常，中纪委国家监委为何\u201c深夜打虎\u201d","date":"2018-04-02 14:52","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/180402145211320.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20180402/20180402145211_0112e1dcb6d8b7ea024ec551aa4ff3a6_1_mwpm_03200403.jpg"},{"uniquekey":"af182ad81a13ba34d5cbbea3fa18fd13","title":"三国死得最可惜的三大名将，实力可傲视三国，却都死于鼠辈之手！","date":"2018-04-02 14:47","category":"头条","author_name":"天源向上","url":"http://mini.eastday.com/mobile/180402144738161.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180402/20180402144738_043c4e914a78cd5925ba9c92c5355901_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180402/20180402144738_043c4e914a78cd5925ba9c92c5355901_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180402/20180402144738_043c4e914a78cd5925ba9c92c5355901_3_mwpm_03200403.jpg"},{"uniquekey":"018a14cca06285e9719965db4accc7bd","title":"被吓了？两月大女婴野生动物园游玩心脏骤停惨死","date":"2018-04-02 14:43","category":"头条","author_name":"看看新闻网","url":"http://mini.eastday.com/mobile/180402144345555.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20180402/20180402144345_d920d63dcc3a345f0fcc8be26056acf1_1_mwpm_03200403.jpg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20180402/20180402144345_d920d63dcc3a345f0fcc8be26056acf1_3_mwpm_03200403.jpg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20180402/20180402144345_d920d63dcc3a345f0fcc8be26056acf1_2_mwpm_03200403.jpg"},{"uniquekey":"b6390882e3f0c1027ea8abad3d18f665","title":"这欧盟大国突然发声拉了俄罗斯一把，英美对俄外交战该偃旗息鼓了","date":"2018-04-02 14:43","category":"头条","author_name":"高峰军事观察","url":"http://mini.eastday.com/mobile/180402144336517.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20180402/20180402_8f624317df8a10b785257c9048800ad2_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20180402/20180402_9301c6926af305619d8b7cd550eaccad_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20180402/20180402_900683aaa8ff0c9afec5ab6fd092c83a_cover_mwpm_03200403.jpg"},{"uniquekey":"910de2fb22e6b47ec837da6ca4f762c0","title":"大陆惠台措施见效 台高中生申请大陆就学人数增加","date":"2018-04-02 14:41","category":"头条","author_name":"央视网","url":"http://mini.eastday.com/mobile/180402144150274.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180402/20180402144150_ddcd6979271b6d157a563e264a1d0be8_1_mwpm_03200403.jpg"},{"uniquekey":"f7e92bfaf50fdab9a53ff0b546f2c628","title":"叙利亚击落以色列战机，是俄罗斯一手主导？此国才是背后真正推手","date":"2018-04-02 14:36","category":"头条","author_name":"猛禽","url":"http://mini.eastday.com/mobile/180402143638281.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20180402/20180402_d0d443b1f3194c4d663bd38f557e9d95_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20180402/20180402_b9444fbf68b77e3bdcaab305b2d475c3_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20180402/20180402_1a06b8a832bb6b20de928e38c2e5e55c_cover_mwpm_03200403.jpg"},{"uniquekey":"1338c997a6c13aa92c0758e23c142976","title":"武侠小说有多坑传统武术？会不会吹和能不能打其实都是生意经！","date":"2018-04-02 14:36","category":"头条","author_name":"冷兵器研究所","url":"http://mini.eastday.com/mobile/180402143600004.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20180402/20180402_58cdb29a60e038b137b87702c7e8b93c_cover_mwpm_03200403.jpg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20180402/20180402_cc21ceed96679245e49a029709d2ab08_cover_mwpm_03200403.jpg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20180402/20180402_cb786bfde9b93879a718b3d2560c5dcb_cover_mwpm_03200403.jpg"},{"uniquekey":"66cd39a7331fa876ace9b65fbe1785c7","title":"驻波黑大使陈波会见波黑议会民族院副主席乔拉克","date":"2018-04-02 14:25","category":"头条","author_name":"驻波黑使馆","url":"http://mini.eastday.com/mobile/180402142558121.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20180402/20180402142558_e11eda1f13659ca92818efa7ca1d87c0_1_mwpm_03200403.jpg"},{"uniquekey":"388b42df19b79f388cfa5a4bba0ddb52","title":"周海媚版灭绝师太现场花絮曝光，每个眼神都透露着霸气与狠毒","date":"2018-04-02 14:22","category":"头条","author_name":"千龙娱乐","url":"http://mini.eastday.com/mobile/180402142228541.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20180402/20180402142228_20ced545c51138a2f56adcda53b219df_2_mwpm_03200403.jpg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20180402/20180402142228_20ced545c51138a2f56adcda53b219df_1_mwpm_03200403.jpg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20180402/20180402142228_20ced545c51138a2f56adcda53b219df_3_mwpm_03200403.jpg"},{"uniquekey":"873c68b0ea980b941e9fae05dcd56b00","title":"4月2日生猪价格继续上涨 我们应当理性看待本次猪价格上涨","date":"2018-04-02 14:22","category":"头条","author_name":"养殖通","url":"http://mini.eastday.com/mobile/180402142226274.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20180402/20180402142226_a4968db92c67a0b3237f69d534c54162_5_mwpm_03200403.jpg","thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20180402/20180402142226_a4968db92c67a0b3237f69d534c54162_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20180402/20180402142226_a4968db92c67a0b3237f69d534c54162_3_mwpm_03200403.jpg"},{"uniquekey":"485a776dea1737bc49f4b6ce23bf4967","title":"守护亿亩良田 金正大集团CEO白瑛详解亲土种植\u201c百千亿行动\u201d","date":"2018-04-02 14:20","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/180402142025496.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180402/20180402142025_ac9d3b728c8ee8be8b4bab77856feb1f_1_mwpm_03200403.jpg"},{"uniquekey":"ed311696843f7aa6a719bde298ad9136","title":"2018\u201c一带一路\u201d与民营企业合作论坛在京举行","date":"2018-04-02 14:12","category":"头条","author_name":"海外网","url":"http://mini.eastday.com/mobile/180402141256871.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20180402/20180402141256_492d190b1261463cea3d437b25c122aa_1_mwpm_03200403.jpg"}]
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
       * uniquekey : cbccd75b53273a61d217aed4354997de
       * title : 广东轿车冲公交站致2死4伤现场
       * date : 2018-04-02 16:24
       * category : 头条
       * author_name : 凤凰网
       * url : http://mini.eastday.com/mobile/180402162409811.html
       * thumbnail_pic_s : http://09.imgmini.eastday.com/mobile/20180402/20180402162409_8f8a8c7743294544d505c3ec371559d0_5_mwpm_03200403.jpg
       * thumbnail_pic_s02 : http://09.imgmini.eastday.com/mobile/20180402/20180402162409_8f8a8c7743294544d505c3ec371559d0_1_mwpm_03200403.jpg
       * thumbnail_pic_s03 : http://09.imgmini.eastday.com/mobile/20180402/20180402162409_8f8a8c7743294544d505c3ec371559d0_3_mwpm_03200403.jpg
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
    }
  }
}
