
/*This is the survey application on android using java and database connectivity.
 * Android has it's own database named SQLite database.
 * This SQLite database is somewhat different from sql database.
 * SQLite takes entry row by row or we can say one at a time.
 * A loop is used for insertion.
 * Similarly a loop is used for deletion.
 */










package com.example.test123;//This is the package where our java file is placed.

/*These are all the header files we included*/
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;



/*The program begins*/
public class test123 extends Activity {
    /** Called when the activity is first created. */
	
	int last;
	private boolean action=false;
	
	//Declaration of all UI elements
	EditText et1;
	TextView textView1;
	Button sub;
	RadioGroup rg4,rg2,rg3,rg8,rg5;/*There are five radio groups namely radio group of 4 radio buttons namely rg4,radio group of 2 radio buttons namely rg2
	similarly rg3, rg8 and rg5*/
	
	
	/*
	 * Radio buttons for each group like, for group 4 there are four radio buttons namely rad40,rad41,rad42,rad43 and similarly for others.
	 */
	RadioButton rad40,rad41,rad42,rad43,rad20,rad21,rad30,rad31,rad32,rad50,rad51,rad52,rad53,rad54,rad80,rad81,rad82,rad83,rad84,rad85,rad86,rad87;
	/*These are the image buttons for the up and down images in this activity*/
	ImageButton imageButton1,imageButton2;
	
	//Database variables
	private final String DB_NAME1 = "dbase1";//name of our database is dbase1
	private final int DB_VERSION = 1;
	private final String DB_PATH="/data/data/com.example.test123/databases/";//this is the location of the database where it will be stored
	SQLiteDatabase db;//Declaration of database object
	 Cursor cursor,rcur;//declaration of cursor variable.A database cursor is a control structure that enables traversal over the records in a database
	 
	 
	//columns of the table named survey//
	private final String TABLE_NAME = "survey";//name of the first table
	private final String TABLE_ROW_ID = "code";
	private final String TABLE_ROW_ONE = "id";
	private final String TABLE_ROW_TWO = "opt1";
	private final String TABLE_ROW_THREE = "opt2";
	private final String TABLE_ROW_FOUR = "opt3";
	private final String TABLE_ROW_FIVE = "opt4";
	private final String TABLE_ROW_SIX = "opt5";
	private final String TABLE_ROW_SEVEN = "opt6";
	private final String TABLE_ROW_EIGHT = "opt7";
	private final String TABLE_ROW_NINE = "opt8";
	private final String TABLE_ROW_TEN = "text";
	private final String TABLE_ROW_ELEVEN = "type";
	
	//columns of the next table named response
	private final String TABLE_NAME1 = "response";//name of the second table
	private final String TABLE_ROW_ONE1 = "surveyid";
	private final String TABLE_ROW_TWO2 = "questionid";
	private final String TABLE_ROW_THREE3 = "answers";
	public static int z=0,flag=0,flag1=1;
	public static String s,h;
	
	
	
	Thread bgthr;//We created a thread to carry out different things because we can't carry different things in one activity only.

	
			
	String[] temp= new String[80];//This is the String Buffer in which we store our answers to the corresponding questions
			
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		
	        // Android specific calls
    		super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	       Log.d(":)", "view is set");
	       bgthr = new Thread(this.bgthread);
	        
	        bgthr.start(); //Here the thread is started.
	       

	}

	
	
	
 Runnable bgthread = new Runnable(){
        
		
		@Override
        public void run()
        {

        	 //Looper.prepare();
	        // make the buttons clicks perform actions
	        while(true)
	        {
	        	getConnect();//We called a method which is used to connect to database.
	        	
	        	
				bgHandler.sendMessage(bgHandler.obtainMessage());//created a handler
	        	
	        	break;
	        }	
        	 
        }
	};
	
        Handler bgHandler = new Handler(){
            public void handleMessage(Message msg) { 
            	
            	test123.this.setupViews();//Called a method to set views
            	test123.this.addButtonListeners();//called a method to define action listener on each and every button
            	
            }
            	
        		
	
        };
	
		/*Method for defining all the views which will be used here*/	
       private void setupViews()
	    {
    	   
    	   
	        // THE DATA FORM FIELDS
	        et1= 		(EditText)findViewById(R.id.et1);
	        textView1= 		(TextView)findViewById(R.id.textView1);
	        
	        imageButton1= (ImageButton)findViewById(R.id.imageButton1);
	        imageButton2= (ImageButton)findViewById(R.id.imageButton2);
	        // THE BUTTONS
	        sub = 		(Button)findViewById(R.id.button1);
	        rg4=        (RadioGroup)findViewById(R.id.rg4);
	        rg2=        (RadioGroup)findViewById(R.id.rg2);
	        rad40=       (RadioButton)findViewById(R.id.rad40);
	        rad41=       (RadioButton)findViewById(R.id.rad41);
	        rad42=       (RadioButton)findViewById(R.id.rad42);
	        rad43=       (RadioButton)findViewById(R.id.rad43);
	        rad20=       (RadioButton)findViewById(R.id.rad20);
	        rad21=       (RadioButton)findViewById(R.id.rad21);
	        
	        rg3=         (RadioGroup)findViewById(R.id.rg3);
	        rad30=       (RadioButton)findViewById(R.id.rad30);
	        rad31=       (RadioButton)findViewById(R.id.rad31);
	        rad32=       (RadioButton)findViewById(R.id.rad32);
	        
	        rg5=         (RadioGroup)findViewById(R.id.rg5);
	        rad50=       (RadioButton)findViewById(R.id.rad50);
	        rad51=       (RadioButton)findViewById(R.id.rad51);
	        rad52=       (RadioButton)findViewById(R.id.rad52);
	        rad53=       (RadioButton)findViewById(R.id.rad53);
	        rad54=       (RadioButton)findViewById(R.id.rad54);
	        
	        rg8=         (RadioGroup)findViewById(R.id.rg8);
	        rad80=       (RadioButton)findViewById(R.id.rad80);
	        rad81=       (RadioButton)findViewById(R.id.rad81);
	        rad82=       (RadioButton)findViewById(R.id.rad82);
	        rad83=       (RadioButton)findViewById(R.id.rad83);
	        rad84=       (RadioButton)findViewById(R.id.rad84);
	        rad85=       (RadioButton)findViewById(R.id.rad85);
	        rad86=       (RadioButton)findViewById(R.id.rad86);
	        rad87=       (RadioButton)findViewById(R.id.rad87);
	    }
	 
       
       /*Method for defining Button listeners*/
	 private void addButtonListeners()
	    {Log.d(":)", "inside add button listener");
	        

	 
		sub.setOnClickListener//this sets an action listener on submit button.
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v)
					{
						//When we click on submit button this whole thing happens.
						
						for(int k=1; k<flag1; k++)
						{
							addRow(k,temp[k]);//called a function which will add row in the database by taking data from string buffer one by one
						}

						Toast.makeText(getBaseContext(), "Successfully fetched", Toast.LENGTH_LONG);
						
						close();
						
						sub.setEnabled(false);
						Intent intent=new Intent(test123.this, final1.class);
						
						
						startActivity(intent);
					}
				}
	        );






	        et1.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {}
				}
	        );
	        
	       /*Action Listener on each and every radio button*/
	        
	        
	        
	        //Action listener on radio buttons of radio group 5
	        rad50.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText5();}
				}
	        );
	        rad51.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText5();}
				}
	        );
	        rad52.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText5();}
				}
	        );
	        rad53.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText5();}
				}
	        );
	        
	        rad54.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText5();}
				}
	        );
	  
	        
	        
	       //Action listener on radio buttons of radio group 4 
	        rad40.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText4();}
				}
	        );
	        rad41.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText4();}
				}
	        );
	        rad42.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText4();}
				}
	        );
	        rad43.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText4();}
				}
	        );
	        
	        
	        
	      //Action listener on radio buttons of radio group 2
	        rad20.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText2();}
				}
	        );
	        rad21.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText2();}
				}
	        );
	        
	        rad30.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText3();}
				}
	        );
	        
	        
	        
	      //Action listener on radio buttons of radio group 3
	        rad31.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText3();}
				}
	        );
	        rad32.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {getText3();}
				}
	        );
	      
	       
	        
	      //Action listener on radio buttons of radio group 8
	        rad80.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {
						Log.d("A:","Inside rad80");
						getText8();}
				}
	        );
	        rad81.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {
						Log.d("A:","Inside rad81");
						getText8();}
				}
	        );
	        rad82.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {
						Log.d("A:","Inside rad82");
						getText8();}
				}
	        );
	        rad83.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {
						Log.d("A:","Inside rad83");
						getText8();}
				}
	        );
	        rad84.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {
						Log.d("A:","Inside rad84");
						getText8();}
				}
	        );
	        rad85.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {
						Log.d("A:","Inside rad85");
						getText8();}
				}
	        );
	        rad86.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {
						Log.d("A:","Inside rad86");
						getText8();}
				}
	        );
	        rad87.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
					@Override public void onClick(View v) {
						Log.d("A:","Inside rad87");
						getText8();}
				}
	        );
	      
	        
	        //Action Listener on imagebutton up 
	       imageButton1.setOnClickListener
	        (
	        	new View.OnClickListener()
		        {
		        	@Override public void onClick(View v) {
		        		if(previous1())
		        		{ 	
		        			
		        		
		        		
		        		if((!(et1.getText().toString().equals(null)))&& et1.isEnabled())//this is the condition to check if edit text is enabled and has some text or not.
    					{

						
    				 		CharSequence s=et1.getText();//get the data from the text field
    				 		String h=s.toString();//convert that data to the string type
						 
						
    				 		
    				 		
    				 		
    				 		if(flag != 0)//if it is on a previous question
    					 	{
    				 			
    				 			
    				 			if(h!=null)
    				 			{
    				 				int a=z+2;
    	    						 Log.d("A","value of z+2 is"+a);
    				 				temp[z+2]=h;
    				 			}				 			
							
    				 		} 
    				 		else//or if it is not on a previous question
    				 		{
    				 			int a=z+2;
    						 
    				 				temp[z+2]=h;
    						
    				 		}
    					 			
    					 		
    					
    				 		et1.setEnabled(false);
    				 		
    					}
		        		
		        		
				 		
		        			
	        			String b=cursor.getString(11);//here it will check the type of question whether it is text or multiple type.
	        			
	        			if(b.equals("m4"))
	        				{
	        					
	        					setText4();
	        				}
	        			else if(b.equals("t"))
	        				{
	        					 
	        					setText1();
	        				}
	        			else if(b.equals("m5"))
	        				setText5();
	        			else if(b.equals("m3"))
	        				setText3();
	        			else if(b.equals("m8"))
	        				setText8();
	        			else 
	        				{
	        					
	        					setText2();
	        				}
		        		}
		        	}
		        }
	        );
	       
	       imageButton2.setOnClickListener//action listener on image button down
	        (
	        	new View.OnClickListener()
		        {/*same as image button up listener*/
		        	@Override public void onClick(View v) {
		        		if(next1())
		        		{ 
		        		
		        		
		        		
		        		
		        		if((!(et1.getText().toString().equals(null)))&& et1.isEnabled())
		        					{
									
		        				 		CharSequence s=et1.getText();
		        				 		String h=s.toString();
									
		        				 		
		        				 		if(flag != 0)
    					 				{
    				 						
										if(h!=null){temp[z]=h;}				 			
							
    				 					} 
    					 				else
    					 					{
    						 
    											 temp[z]=h;
    						
    										 }
		        					 			
		        					 		
		        					
		        				 			et1.setEnabled(false);
		        				 		
		        					}
		        		
		        		
		        		
		          			String a=cursor.getString(11);
		        			
		        			if(a.equals("m4"))
		        				{
		        					
		        					
									setText4();
		        						
		        				}
		        			else if(a.equals("t"))
		        				{
		        					
		        					setText1();
		        				}
		        			else if(a.equals("m5"))
		        				setText5();
		        			else if(a.equals("m3"))
		        				setText3();
		        			else if(a.equals("m8"))
		        				setText8();
		        			else 
		        				{
		        					
		        					setText2();
		        				}
		        		}
		        	}
		        }
	        
	   			
		);}
	        
	 
	  
	 private void getConnect()
	 {try{
		 
		 String myPath = DB_PATH + DB_NAME1;
		 db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);//Here is the database opened as read write
		 cursor= db.query(
					TABLE_NAME,
					new String[] { TABLE_ROW_ID, TABLE_ROW_ONE, TABLE_ROW_TWO, TABLE_ROW_THREE, TABLE_ROW_FOUR, TABLE_ROW_FIVE, TABLE_ROW_SIX, TABLE_ROW_SEVEN, TABLE_ROW_EIGHT, TABLE_ROW_NINE, TABLE_ROW_TEN, TABLE_ROW_ELEVEN},
					null, null, null, null, null

			);//here is the query for cursor variable
		 
		 cursor.moveToFirst();//cursor will move to first row
		 
		 
		 last=0;
			
			while(cursor.moveToNext())
			{
				last++;
			}
			
			//this the query for taking all the information from the database and store it in a cursor so that cursor can transverse over the whole database table.
			cursor= db.query(
					TABLE_NAME,
					new String[] { TABLE_ROW_ID, TABLE_ROW_ONE, TABLE_ROW_TWO, TABLE_ROW_THREE, TABLE_ROW_FOUR, TABLE_ROW_FIVE, TABLE_ROW_SIX, TABLE_ROW_SEVEN, TABLE_ROW_EIGHT, TABLE_ROW_NINE, TABLE_ROW_TEN, TABLE_ROW_ELEVEN},
					null, null, null, null, null

			);

			if(!(last>0))
			{
				action=false;
				
			}
			else
			{
				action=true;
				cursor.moveToNext();				
			}
	    }catch(Exception e){e.printStackTrace();}
	 }
	 private void setText4()
	 {//This is the method for setting the text for question which has four options 
		try{
			//making the radio group and radio buttons visible
			rg4.setVisibility(View.VISIBLE);
			rad40.setVisibility(View.VISIBLE);
			rad41.setVisibility(View.VISIBLE);
			rad42.setVisibility(View.VISIBLE);
			rad43.setVisibility(View.VISIBLE);
			
			
			
			
			rg4.clearCheck();//clears the previous selection
			
			//setting other views to invisible or gone
			et1.setVisibility(View.GONE);
			rg2.setVisibility(View.GONE);
			rg3.setVisibility(View.GONE);
			rg5.setVisibility(View.GONE);
			rg8.setVisibility(View.GONE);
			
		//setting the question and it's four options on the screen from the database with the help of cursor	
		 textView1.setText(cursor.getString(10));
		 rad40.setText( cursor.getString(2));
		 rad41.setText(cursor.getString(3));
		 rad42.setText(cursor.getString(4));
		 rad43.setText(cursor.getString(5));
		 
	 	}
		catch(Exception e)
	 	{
			e.printStackTrace();
			
	 	}
		
	 }
	 
	 private boolean next1()
		{/*This method is called when we click next button. This method  is moving cursor to next question in the database*/
			try
			{  z++;//This variable is used for array index. On clicking next it goes to next question and increases by one.
				if(flag==0)
				{
					flag1++;//This flag is used to get a track of the questions which are visited.
				}
			
			while(flag>0)
			 {
				 flag--;//This flag is used to keep the track of question which are answered or not
			 }
			h=null;
			
			
				imageButton1.setClickable(true);
				 
				boolean t=cursor.isLast();//if the cursor is on last question
				if(t)
				{
					imageButton2.setClickable(false);
					Toast.makeText(this.getBaseContext(), "no questions available", Toast.LENGTH_LONG);
					return false;
				}
				else
				{
					cursor.moveToNext();//cursor will move to next question.
					
					
					return true;
				}
			}
			catch(Exception ex)
			{
				Toast.makeText(this.getBaseContext(), "SQL ERROR",Toast.LENGTH_LONG);	
				return false;
			}
		}
	
	 private void setText1()
	 {	//This is the method for setting descriptive type question
	 	et1.setEnabled(true);
	 	 
	 	 //setting the other views as invisible or gone
	 	 rg4.setVisibility(View.GONE);
		 rg2.setVisibility(View.GONE);
		 rg3.setVisibility(View.GONE);
		 rg5.setVisibility(View.GONE);
		 rg8.setVisibility(View.GONE);
		 
		 
		 et1.setVisibility(View.VISIBLE);//setting this to visible
		 et1.setText("");//clearing the previous selection
		 textView1.setText(cursor.getString(10));//setting the question from the database
		 
	 }
	 /*similarly all the settext are same*/
	 private void setText2()
	 {
	
	 	
		 rg2.setVisibility(View.VISIBLE);
		 rad20.setVisibility(View.VISIBLE);
			rad21.setVisibility(View.VISIBLE);
		 
			
			
			
			
			rg2.clearCheck();
		 et1.setVisibility(View.GONE);
			rg4.setVisibility(View.GONE);
			rg3.setVisibility(View.GONE);
			rg5.setVisibility(View.GONE);
			rg8.setVisibility(View.GONE);
		 textView1.setText(cursor.getString(10));
		 rad20.setText(cursor.getString(2));
		 rad21.setText(cursor.getString(3));
		
		 	
	 }
	 
	 private void setText3()
	 {
	 
	 
		 rg3.setVisibility(View.VISIBLE);
		 rad30.setVisibility(View.VISIBLE);
			rad31.setVisibility(View.VISIBLE);
			rad32.setVisibility(View.VISIBLE);
		 
			
			
			
			rg3.clearCheck();
		 et1.setVisibility(View.GONE);
			rg4.setVisibility(View.GONE);
			rg2.setVisibility(View.GONE);
			rg5.setVisibility(View.GONE);
			rg8.setVisibility(View.GONE);
		 textView1.setText(cursor.getString(10));
		 rad30.setText(cursor.getString(2));
		 rad31.setText(cursor.getString(3));
		 rad32.setText(cursor.getString(4));
		
	 }
	 
	 private void setText5()
	 {
	 
	 	
		 rg5.setVisibility(View.VISIBLE);
		 rad50.setVisibility(View.VISIBLE);
			rad51.setVisibility(View.VISIBLE);
			rad52.setVisibility(View.VISIBLE);
			rad53.setVisibility(View.VISIBLE);
			rad54.setVisibility(View.VISIBLE);
			
			
		 rg5.clearCheck();
		 et1.setVisibility(View.GONE);
			rg4.setVisibility(View.GONE);
			rg3.setVisibility(View.GONE);
			rg2.setVisibility(View.GONE);
			rg8.setVisibility(View.GONE);
		 textView1.setText(cursor.getString(10));
		 rad50.setText(cursor.getString(2));
		 rad51.setText(cursor.getString(3));
		 rad52.setText(cursor.getString(4));
		 rad53.setText(cursor.getString(5));
		 rad54.setText(cursor.getString(6));
		 
			
	 }
	 
	 private void setText8()
	 {
	 	
		 rg8.setVisibility(View.VISIBLE);
		    
		    rad80.setVisibility(View.VISIBLE);
			rad81.setVisibility(View.VISIBLE);
			rad82.setVisibility(View.VISIBLE);
			rad83.setVisibility(View.VISIBLE);
			rad84.setVisibility(View.VISIBLE);
			rad85.setVisibility(View.VISIBLE);
			rad86.setVisibility(View.VISIBLE);
			rad87.setVisibility(View.VISIBLE);
			
			
		 rg8.clearCheck();
		 et1.setVisibility(View.GONE);
			rg4.setVisibility(View.GONE);
			rg3.setVisibility(View.GONE);
			rg5.setVisibility(View.GONE);
			rg2.setVisibility(View.GONE);
		 textView1.setText(cursor.getString(10));
		 rad80.setText(cursor.getString(2));
		 rad81.setText(cursor.getString(3));
		 rad82.setText(cursor.getString(4));
		 rad83.setText(cursor.getString(5));
		 rad84.setText(cursor.getString(6));
		 rad85.setText(cursor.getString(7));
		 rad86.setText(cursor.getString(8));
		 rad87.setText(cursor.getString(9));
		 
		
		    
	 }
	 
	 private boolean previous1()
		{/*This function is called when we click on previous imagebutton .*/
			try
			{z--;//This variable is for the array index.
			flag++;/*this flag increases when we click on previous button.This flag is used to determine whether we will answer the previous 
					question or not.*/
			
				h=null;	
			
				imageButton2.setClickable(true);
				
				
				
				boolean t=cursor.isFirst();//if cursor is on first question
				if(t)
				{	
						imageButton1.setEnabled(false);return true;
					
					
				}
				else
				{
					cursor.moveToPrevious();//cursor moved to previous question
					
					
					return true;
				}
			}
			catch(Exception ex)
			{
				Toast.makeText(this.getBaseContext(), "SQL ERROR",Toast.LENGTH_LONG);	
				return false;
			}
		}
	 
	 private void getText4()
	 {/*This method is for getting the text from radio button of radio group 4*/
		
		 
			 int a= rg4.getCheckedRadioButtonId();
			 Log.d("a","value of checked a is "+a+"");
			 CharSequence s = null;
			
			 for(int i=0;i<4;i++)
			 {
				 RadioButton btn=(RadioButton) rg4.getChildAt(i);
				 	if(btn.isChecked()==true)
				 	{	
				 		s=btn.getText();
				 		
				 		h=s.toString();
						temp[z+1]=h;//putting the string into string array from a corresponding radio button
				 		
				 		break;
				 	}
			 }
			 
	 }
	 private void getText2()
	 {/*This method is for getting the text from radio button of radio group 2*/
		 
		 
			 int a= rg2.getCheckedRadioButtonId();
			 
			 CharSequence s=null;
			 for(int i=0;i<2;i++)
			 {
				 RadioButton btn=(RadioButton) rg2.getChildAt(i);
				 	if(btn.isChecked()==true)
				 	{
				 		s=btn.getText();
				 		
				 		h=s.toString();
						temp[z+1]=h;
				 		break;
				 	}
			 }
			

	 }
	 private void getText3()
	 {
		
			 int a= rg3.getCheckedRadioButtonId();
			
			 CharSequence s=null;
			 for(int i=0;i<3;i++)
			 {
				 RadioButton btn=(RadioButton) rg3.getChildAt(i);
				 	if(btn.isChecked()==true)
				 	{
				 		s=btn.getText();
				 		
				 		h=s.toString();
						temp[z+1]=h;
				 		break;
				 	}
			 }
			 

	 }
	 private void getText5()
	 {
		 
			 int a= rg5.getCheckedRadioButtonId();
			 
			 CharSequence s=null;
		 
			 for(int i=0;i<5;i++)
			 {
				 RadioButton btn=(RadioButton) rg5.getChildAt(i);
				 	if(btn.isChecked()==true)
				 	{
				 		s=btn.getText();
				 		
				 		h=s.toString();

						temp[z+1]=h;
				 		break;
				 	}
			 }
			 
	 }
	 private void getText8()
	 {
		
		 
			 int a= rg8.getCheckedRadioButtonId();
			
			 CharSequence s = null;
			 for(int i=0;i<8;i++)
			 {
				 RadioButton btn=(RadioButton) rg8.getChildAt(i);
				 	if(btn.isChecked()==true)
				 	{
				 		s=btn.getText();
				 		
				 		h=s.toString();
						temp[z+1]=h;
				 		break;
				 	}
				 	
			 }
			 

	 }
	 
		 
	 private void addRow(int rowStringOne,String rowStringTwo)
	 {/*This function is called when submit button is clicked. This function simply adds the string array to the response table of database dbase1*/
		 ContentValues values = new ContentValues();
		 
			// this is how you add a value to a ContentValues object
			// we are passing in a key string and a value string each time
		 	
		 	
		 	values.put(TABLE_ROW_ONE1, final1.id1);
			values.put(TABLE_ROW_TWO2, rowStringOne);
			values.put(TABLE_ROW_THREE3, rowStringTwo);
			
			// ask the database object to insert the new data 
			try
			{
				
				db.insert(TABLE_NAME1, null, values);
				
				
			}
			catch(Exception e)
			{
				Log.e("DB ERROR", e.toString()); // prints the error message to the log
				e.printStackTrace(); // prints the stack trace to the log
			}

	 }
	 
	 private void close()
	 {
		 db.close();//closing the database object
		 cursor.close();//closing the cursor
	 }
	 
	
}
