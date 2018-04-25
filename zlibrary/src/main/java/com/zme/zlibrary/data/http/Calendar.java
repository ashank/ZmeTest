package com.zme.zlibrary.data.http;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * 作者：zhiyahan on 2018/3/27 15:05
 */
public class Calendar {

  /**
   * year : 2017
   * month : 2
   * day : 2
   * lunarYear : 2017
   * lunarMonth : 1
   * lunarDay : 6
   * cnyear : 贰零壹柒
   * cnmonth : 正
   * cnday : 初六
   * hyear : 丁酉
   * cyclicalYear : 丙申
   * cyclicalMonth : 辛丑
   * cyclicalDay : 庚申
   * suit : 纳采,订盟,祭祀,求嗣,出火,塑绘,裁衣,会亲友,入学,拆卸,扫舍,造仓,挂匾,掘井,开池,结网,栽种,纳畜,破土,修坟,立碑,安葬,入殓
   * taboo : 祈福,嫁娶,造庙,安床,谢土
   * animal : 鸡
   * week : 星期四
   * festivalList : []
   * jieqi : {"4":"立春","19":"雨水"}
   * maxDayInMonth : 29
   * leap : false
   * bigMonth : false
   * lunarYearString : 丁酉
   */

  private int year;
  private int month;
  private int day;
  private int lunarYear;
  private int lunarMonth;
  private int lunarDay;
  private String cnyear;
  private String cnmonth;
  private String cnday;
  private String hyear;
  private String cyclicalYear;
  private String cyclicalMonth;
  private String cyclicalDay;
  private String suit;
  private String taboo;
  private String animal;
  private String week;
  private JieqiEntity jieqi;
  private int maxDayInMonth;
  private boolean leap;
  private boolean bigMonth;
  private String lunarYearString;
  private List<?> festivalList;

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getLunarYear() {
    return lunarYear;
  }

  public void setLunarYear(int lunarYear) {
    this.lunarYear = lunarYear;
  }

  public int getLunarMonth() {
    return lunarMonth;
  }

  public void setLunarMonth(int lunarMonth) {
    this.lunarMonth = lunarMonth;
  }

  public int getLunarDay() {
    return lunarDay;
  }

  public void setLunarDay(int lunarDay) {
    this.lunarDay = lunarDay;
  }

  public String getCnyear() {
    return cnyear;
  }

  public void setCnyear(String cnyear) {
    this.cnyear = cnyear;
  }

  public String getCnmonth() {
    return cnmonth;
  }

  public void setCnmonth(String cnmonth) {
    this.cnmonth = cnmonth;
  }

  public String getCnday() {
    return cnday;
  }

  public void setCnday(String cnday) {
    this.cnday = cnday;
  }

  public String getHyear() {
    return hyear;
  }

  public void setHyear(String hyear) {
    this.hyear = hyear;
  }

  public String getCyclicalYear() {
    return cyclicalYear;
  }

  public void setCyclicalYear(String cyclicalYear) {
    this.cyclicalYear = cyclicalYear;
  }

  public String getCyclicalMonth() {
    return cyclicalMonth;
  }

  public void setCyclicalMonth(String cyclicalMonth) {
    this.cyclicalMonth = cyclicalMonth;
  }

  public String getCyclicalDay() {
    return cyclicalDay;
  }

  public void setCyclicalDay(String cyclicalDay) {
    this.cyclicalDay = cyclicalDay;
  }

  public String getSuit() {
    return suit;
  }

  public void setSuit(String suit) {
    this.suit = suit;
  }

  public String getTaboo() {
    return taboo;
  }

  public void setTaboo(String taboo) {
    this.taboo = taboo;
  }

  public String getAnimal() {
    return animal;
  }

  public void setAnimal(String animal) {
    this.animal = animal;
  }

  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }

  public JieqiEntity getJieqi() {
    return jieqi;
  }

  public void setJieqi(JieqiEntity jieqi) {
    this.jieqi = jieqi;
  }

  public int getMaxDayInMonth() {
    return maxDayInMonth;
  }

  public void setMaxDayInMonth(int maxDayInMonth) {
    this.maxDayInMonth = maxDayInMonth;
  }

  public boolean isLeap() {
    return leap;
  }

  public void setLeap(boolean leap) {
    this.leap = leap;
  }

  public boolean isBigMonth() {
    return bigMonth;
  }

  public void setBigMonth(boolean bigMonth) {
    this.bigMonth = bigMonth;
  }

  public String getLunarYearString() {
    return lunarYearString;
  }

  public void setLunarYearString(String lunarYearString) {
    this.lunarYearString = lunarYearString;
  }

  public List<?> getFestivalList() {
    return festivalList;
  }

  public void setFestivalList(List<?> festivalList) {
    this.festivalList = festivalList;
  }

  public static class JieqiEntity {

    /**
     * 4 : 立春
     * 19 : 雨水
     */

    @SerializedName("4")
    private String _$4;
    @SerializedName("19")
    private String _$19;

    public String get_$4() {
      return _$4;
    }

    public void set_$4(String _$4) {
      this._$4 = _$4;
    }

    public String get_$19() {
      return _$19;
    }

    public void set_$19(String _$19) {
      this._$19 = _$19;
    }

    @Override
    public String toString() {
      return "JieqiEntity{" +
          "_$4='" + _$4 + '\'' +
          ", _$19='" + _$19 + '\'' +
          '}';
    }
  }

  @Override
  public String toString() {
    return "Calendar{" +
        "year=" + year +
        ", month=" + month +
        ", day=" + day +
        ", lunarYear=" + lunarYear +
        ", lunarMonth=" + lunarMonth +
        ", lunarDay=" + lunarDay +
        ", cnyear='" + cnyear + '\'' +
        ", cnmonth='" + cnmonth + '\'' +
        ", cnday='" + cnday + '\'' +
        ", hyear='" + hyear + '\'' +
        ", cyclicalYear='" + cyclicalYear + '\'' +
        ", cyclicalMonth='" + cyclicalMonth + '\'' +
        ", cyclicalDay='" + cyclicalDay + '\'' +
        ", suit='" + suit + '\'' +
        ", taboo='" + taboo + '\'' +
        ", animal='" + animal + '\'' +
        ", week='" + week + '\'' +
        ", jieqi=" + jieqi +
        ", maxDayInMonth=" + maxDayInMonth +
        ", leap=" + leap +
        ", bigMonth=" + bigMonth +
        ", lunarYearString='" + lunarYearString + '\'' +
        ", festivalList=" + festivalList +
        '}';
  }
}
