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

package com.funhotel.hmvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.funhotel.hmvp.ui.fragement.NewFragment;
import java.util.List;

/**
 * ViewPaper的适配器
 *
 * @author Administrator
 */
public class ViewPaperAdapter extends FragmentPagerAdapter {

  private List<NewFragment> fragments;
  private List<String> newTypes;
  private int[] redIds;


  public ViewPaperAdapter(FragmentManager fm, List<NewFragment> list, List<String> newTypes,
      int[] resIds) {
    super(fm);
    // TODO Auto-generated constructor stub
    this.fragments = list;
    this.newTypes = newTypes;
    this.redIds = resIds;
  }

  @Override
  public Fragment getItem(int arg0) {
    // TODO Auto-generated method stub
    return fragments.get(arg0);
  }

  @Override
  public int getCount() {
    // TODO Auto-generated method stub
    return fragments.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    // TODO Auto-generated method stub
    return newTypes.get(position);
  }

  public void setFragments(List<NewFragment> fragments) {
    this.fragments = fragments;
  }


  public void setTitles(List<String> newTypes) {
    this.newTypes = newTypes;
  }

	
	
/*	@Override
	public int getPageIconResId(int position) {
		// TODO Auto-generated method stub
		return redIds[position];
	}
	*/


}
