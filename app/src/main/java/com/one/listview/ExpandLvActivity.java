package com.one.listview;

import com.one.R;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

public class ExpandLvActivity extends Activity {

	private ExpandableListView expandlistView;
	private ExpandLvAdapter statusAdapter;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expand_listview);
		context = this;
		expandlistView = (ExpandableListView) findViewById(R.id.expandlist);
		initExpandListView();
	}

	/**
	 * 初始化可拓展列表
	 */
	private void initExpandListView() {
		statusAdapter = new ExpandLvAdapter(context, getListData());
		expandlistView.setAdapter(statusAdapter);
		expandlistView.setGroupIndicator(null); // 去掉默认带的箭头
		expandlistView.setSelection(0);// 设置默认选中项
		// 遍历所有group,将所有项设置成默认展开
		int groupCount = expandlistView.getCount();
		for (int i = 0; i < groupCount; i++) {
			expandlistView.expandGroup(i);
		}

		expandlistView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}

	@Override
	public void onBackPressed() 
	{
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}
	
	private List<GroupModel> getListData() {
		List<GroupModel> groupList;
		String[] strArray = new String[]{"10月22日", "10月23日", "10月25日", "10月22日", "10月23日",
				"10月25日"};
		String[][] childTimeArray = new String[][]{
				{"俯卧撑十次", "仰卧起坐二十次", "大喊我爱紫豪二十次", "每日赞紫豪一次"},
				{"亲，快快滴点赞哦~", "仰卧起坐二十次", "水电费", "松岛枫"},
				{"没有赞的，赶紧去赞哦~", "亲，快快滴点赞哦~", "仰卧起坐二十次", "水电费", "松岛枫"},
				{"俯卧撑十次", "仰卧起坐二十次", "大喊我爱紫豪二十次", "每日赞紫豪一次"},
				{"亲，快快滴点赞哦~", "仰卧起坐二十次", "水电费", "松岛枫"},
				{"没有赞的，赶紧去赞哦~", "亲，快快滴点赞哦~", "仰卧起坐二十次", "水电费", "松岛枫"}
		};
		groupList = new ArrayList<GroupModel>();
		for (int i = 0; i < strArray.length; i++) {
			GroupModel groupStatusEntity = new GroupModel();
			groupStatusEntity.setGroupName(strArray[i]);

			List<ChildModel> childList = new ArrayList<ChildModel>();

			for (int j = 0; j < childTimeArray[i].length; j++) {
				ChildModel childStatusEntity = new ChildModel();
				childStatusEntity.setCompleteTime(childTimeArray[i][j]);
				childStatusEntity.setIsfinished(true);
				childList.add(childStatusEntity);
			}

			groupStatusEntity.setChildList(childList);
			groupList.add(groupStatusEntity);
		}
		return groupList;
	}
}