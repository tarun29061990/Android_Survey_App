/*This is the entry screen.First it will appear and then when we click start then our next activity appears which is test123 */






package com.example.test123;//this is the name of the package where we have kept this file


/*concerned header files*/
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class final1 extends Activity
{	
	//Declaration of all UI elements
	Button button1;
	TextView textView1,textView2,textView3,textView4,textView5;
	EditText et1,et2,et3,et4,et5;
	Thread bg;//created a thread to do multiple tasks
	
	int last;
	private boolean action=false;
	
	//Database variables//
	 public static SQLiteDatabase db;
	public static Cursor cursor,cur;
	private final String DB_NAME1 = "dbase1";
	private final int DB_VERSION = 1;
	private final String DB_PATH="/data/data/com.example.test123/databases/";
	
	//columns of database table//
	private final String TABLE_NAME = "survey_inf";//name of table 1
	private final String TABLE_ROW_ID1 = "surveyid";
	private final String TABLE_ROW_ID = "dateofsurvey";
	private final String TABLE_ROW_ONE = "groupno";
	private final String TABLE_ROW_TWO = "nameofsurveyer";
	private final String TABLE_ROW_THREE = "surveyno";
	private final String TABLE_ROW_FOUR = "wardno";
	
	private final String TABLE_NAME1 = "response";//name of table 2
	private final String TABLE_ROW_ONE1="surveyid";
	private final String TABLE_ROW_TWO2="questionid";
	private final String TABLE_ROW_THREE3="answers";
	public static int id1;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entry);
		
		bg = new Thread(this.bgthread);
        
        bg.start(); 
		
	}
	
private Runnable bgthread = new Runnable(){
        
		
		

		@Override
        public void run()
        {

        	
	        while(true)
	        {	try{
	        	CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(getBaseContext());
	    		
	    		db = helper.getWritableDatabase();
	    		String myPath = DB_PATH + DB_NAME1;
				db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
				
	        }catch(Exception e){e.printStackTrace();}
	  
	        	
				bgHandler.sendMessage(bgHandler.obtainMessage());
	        	
	        	break;
	        }	
        	 
        }
	};
	
        Handler bgHandler = new Handler(){
            public void handleMessage(Message msg) { 
            	
            	final1.this.setupView();
            	final1.this.addAction();
            	
            }
            	
        		
	
        };
	
	private void setupView()//method to set the views
	{
		button1 = (Button)findViewById(R.id.button1);
		textView1 = (TextView)findViewById(R.id.textView1);
		textView2 = (TextView)findViewById(R.id.textView2);
		textView3 = (TextView)findViewById(R.id.textView3);
		textView4 = (TextView)findViewById(R.id.textView4);
		textView5 = (TextView)findViewById(R.id.textView5);
		
		et1 = (EditText)findViewById(R.id.et1);
		et2 = (EditText)findViewById(R.id.et2);
		et3 = (EditText)findViewById(R.id.et3);
		et4 = (EditText)findViewById(R.id.et4);
		et5 = (EditText)findViewById(R.id.et5);
	}
	private void addAction()//method to define action listener
	{
		button1.setOnClickListener(new OnClickListener(){
			//sets the on click listener on the start button
			@Override
			public void onClick(View v) {
				if(inftodatabase(et2.getText().toString(),et5.getText().toString(),et1.getText().toString(),et3.getText().toString(),et4.getText().toString()))
				{
					close();//this method used to close the database
					finish();
					Intent intent=new Intent(final1.this, test123.class);//Created a intent for starting the activity namely that question answers screen
					
					
					startActivity(intent);//finally starting the intent
				}
			}
        	
        });
	}
	/*This function is used to insert the information into database.This function is called when we click start*/
	 private boolean inftodatabase(String rowStringOne,String rowStringTwo,String rowStringThree,String rowStringFour,String rowStringFive)
	 {
		//this is a query for the survey_inf table//
			cursor= db.query(
					TABLE_NAME,
					new String[] { TABLE_ROW_ID1,TABLE_ROW_ID, TABLE_ROW_ONE, TABLE_ROW_TWO, TABLE_ROW_THREE, TABLE_ROW_FOUR},
					null, null, null, null, null
			);
			
			
			
			cursor.moveToLast();//cursor move to the last row
			if(cursor.getInt(0)==0)
			{
				id1=0;//this variable is for no. of surveys
				id1++;
				
			}
			else
				{
					id1=cursor.getInt(0);
					
					id1++;
					
				}
			
				
			
			
			/*All the insertion begins*/
			ContentValues values = new ContentValues();
			values.put(TABLE_ROW_ID1, id1);
			values.put(TABLE_ROW_ID, rowStringOne);
			values.put(TABLE_ROW_ONE, rowStringTwo);
			values.put(TABLE_ROW_TWO, rowStringThree);
			values.put(TABLE_ROW_THREE, rowStringFour);
			values.put(TABLE_ROW_FOUR, rowStringFive);
			
			ContentValues values1 = new ContentValues();
			values1.put(TABLE_ROW_ONE1,id1);
			values1.put(TABLE_ROW_TWO2,0);
			values1.put(TABLE_ROW_THREE3,0);
			Log.d("A","above exception");
			// ask the database object to update the database row of given rowID
			
			long test = 0;
			try {
				Log.d("A","TABLE_NAME " + TABLE_NAME);
				if(values.equals(null))
				{
					Log.d("A","after fetching values NULL");
				}
				else
				{
				 db.insert(TABLE_NAME,null,values);
				 
				 db.insert(TABLE_NAME1,null,values1);
				Log.d("A","after fetching " + test);
				}
				
				 Log.d("A","successfully fetched");
				 return true;
				}
			catch (Exception e)
			{
				Log.e("DB Error", e.toString());
				e.printStackTrace();
				return false;
			}

	 }
	 private void close()
	 {
		 db.close();//closing the database object
		 cursor.close();//closing the cursor object
		 
		 
	 }
	 private class CustomSQLiteOpenHelper extends SQLiteOpenHelper
		{
			public CustomSQLiteOpenHelper(Context context)
			{
				super(context, DB_NAME1, null, DB_VERSION);
			}
	 
			

			@Override
			public void onCreate(SQLiteDatabase db) {
				
				// TODO Auto-generated method stub
				//String myPath = DB_PATH + DB_NAME1;
				//db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
			}
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
			{
				// NOTHING TO DO HERE. THIS IS THE ORIGINAL DATABASE VERSION.
				// OTHERWISE, YOU WOULD SPECIFIY HOW TO UPGRADE THE DATABASE.
			}

		}
}