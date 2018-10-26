package com.stone.verticalslide;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;

import com.stone.verticalslide.DragLayout.ShowNextPageNotifier;

public class MainActivity extends FragmentActivity {
	private static final  String rClassName = MainActivity.class.getSimpleName();

	private VerticalFragment1 fragment1;
	private VerticalFragment3 fragment3;
	private DragLayout draglayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (null == savedInstanceState) {
			Log.d(rClassName,"onCreate no saved previous state");
		}else{
			Log.d(rClassName,"onCreate restoring previous state");
		}
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * 初始化View
	 */
	private void initView() {
		try{
			fragment1 = new VerticalFragment1();
			fragment3 = new VerticalFragment3();

			getSupportFragmentManager().beginTransaction()
					.add(R.id.first, fragment1).add(R.id.second, fragment3)
					.commit();

			ShowNextPageNotifier nextIntf = new ShowNextPageNotifier() {
				@Override
				public void onDragNext() {
					fragment3.initView();
				}
			};
			draglayout = (DragLayout) findViewById(R.id.draglayout);
			draglayout.setNextPageListener(nextIntf);

		}catch (Exception e){
			Log.d(rClassName,"initView()",e);
		}

	}

}
