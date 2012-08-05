package com.example.application1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	int total;		//計算結果
	int random;		//表示する乱数
	int count;		//表示回数
	int delaytime;	//表示間隔
	TextView view = null;
	SampleHandler handler = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        view = (TextView) this.findViewById(R.id.display2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    /**
     * start
     * 手動
     */
    public void startKeyOnClick(View v){
    	RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
    	RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
    	switch(radioButton.getId()){								
		case R.id.radiobutton1:
			System.out.println("低");
			random = (int)Math.floor(Math.random() * 9) + 1;
	    	total += random;
			break;
		case R.id.radiobutton2:
			System.out.println("中");
			random = (int)Math.floor(Math.random() * 10) + 10;
	    	total += random;
			break;
		case R.id.radiobutton3:
			System.out.println("高");
			random = (int)Math.floor(Math.random() * 8 + 2) * 10 + (int)Math.floor(Math.random() * 10);
	    	total += random;
			break;
    	}
    	
    	view.setText(Integer.toString(random));
    	
    	System.out.println("計算結果:" + total);
    }
    
    /**
     * end
     * 答え、リセット
     */
    public void endKeyOnClick(View v){

    	//TextView view = (TextView)this.findViewById(R.id.display2);
    	view.setText(Integer.toString(total));
    	
    	System.out.println("計算結果:" + total);
    	total = 0;
    	
    	Button button = (Button)v;
    	if(button.getText().toString().equals("答え")){
    		button.setText("リセット");
    	}else{
    		button.setText("答え");
    	}
    }
    
    /**
     * reset
     * リセット
     /
    public void resetKeyOnClick(View v){
    	
    	total = 0;

    	//TextView view = (TextView)this.findViewById(R.id.display2);
    	view.setText(Integer.toString(total));
    	
    	System.out.println("計算結果:" + total);
    	
    }
    
    /**
     * auto
     * 自動
     */
    public void autoKeyOnClick(View v){
    	Button button = (Button)v;
    	switch(button.getId()){								
		case R.id.autobutton1:
			delaytime = 100;
			System.out.println(delaytime);
			break;
		case R.id.autobutton2:
			delaytime = 500;
			System.out.println(delaytime);
			break;
		case R.id.autobutton3:
			delaytime = 1000;
			System.out.println(delaytime);
			break;
    	}
    	
    	count = 0;

    	handler = new SampleHandler();
    	handler.sleep(0);
    }

    
	/**
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		handler = new SampleHandler();
	}

	/**
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();
		handler = null;
	}

    
	public class SampleHandler extends Handler {
		boolean flag = true;

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			view.invalidate();
			if (handler != null) handler.sleep(delaytime);
			
			System.out.println(++count);
			if(count > 10-2){
				handler = null;
			}

			if(flag){
				RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		    	RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
		    	switch(radioButton.getId()){								
				case R.id.radiobutton1:
					System.out.println("低");
					random = (int)Math.floor(Math.random() * 9) + 1;
			    	total += random;
					break;
				case R.id.radiobutton2:
					System.out.println("中");
					random = (int)Math.floor(Math.random() * 10) + 10;
			    	total += random;
					break;
				case R.id.radiobutton3:
					System.out.println("高");
					random = (int)Math.floor(Math.random() * 8 + 2) * 10 + (int)Math.floor(Math.random() * 10);
			    	total += random;
					break;
		    	}
				view.setText(Integer.toString(random));
				flag = false;
				
			}else{
				view.setText("");
				flag = true;
			}
			
		}
	
		public void sleep(long delay){
			this.removeMessages(0);									//既にあるMessageを削除
			this.sendMessageDelayed(this.obtainMessage(0), delay);	//delayの時間後にMessageを発行する	
		}

	}
    
}
    
    
//try{
//Thread.sleep(2000);
//}catch(Exception e){
//System.out.print(e);
//}
    
// test