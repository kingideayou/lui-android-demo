package cn.iolove.android.lui.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.iolove.android.lui.Context.RuntimeContext;
import cn.iolove.android.lui.lua.Luahelper;
import cn.iolove.android.lui.model.Luadata;
import cn.iolove.android.lui.model.ViewModel;
import cn.iolove.android.lui.utils.Utils;
import cn.iolove.android.lui.utils.WidgetController;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;


import android.widget.LinearLayout;


public class View extends  BaseView{


	private List subitems = new ArrayList();
	protected RuntimeContext context;

	private ViewModel viewmodel;
	//LinearLayout l;
	public View(RuntimeContext context) {
		super(context.GetActivityContext());
		this.context =  context;
	}
	public ViewModel getViewModel()
	{
		return viewmodel;
	}
	public void addChild(BaseView v)
	{
		subitems.add(v);
		 
	}
	public String toString()
	{
	   return viewmodel.toString();
		
	}
   public void setViewData(Luadata temp)
   {
	   Class c1;
	   try {
		c1 = Class.forName("cn.iolove.android.lui.model."+temp.getAttrs().get("qName")+"Model");
		Class[] params = {  }; 
		Object [] parray={};
		Constructor cons=c1.getConstructor(params);
		viewmodel = (ViewModel) cons.newInstance(parray);

		
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		Log.i("jjj", "动态生成viewModel对象出错");
		e1.printStackTrace();
	}


	   viewmodel.setAttrs(temp.getAttrs());

		if(!(viewmodel.getValueByKey("Id")).equals(""))
		{
			
			Utils.setGlobalObject(context.getLuaState()
					, viewmodel.getValueByKey("Id"),viewmodel);
		}
		
	   if(temp.hasChild())
	   {
		   for(int i=0;i<temp.getChild().size();i++)
		   {
			   BaseView  child= new View(context);
			   
			   Luadata cd= (Luadata) temp.getChild().get(i);
			   Class c2;
			   try
			   {
				   if(cd.getAttrs().get("qName").equals("View"))
				   {
					    c2 = Class.forName("cn.iolove.android.lui.view."+"View");
				   }
				   else
				   {
					    c2 = Class.forName("cn.iolove.android.lui.view."+cd.getAttrs().get("qName")+"View");
				   }
				   Class[] paramTypes = { RuntimeContext.class };  
				   Object[] params = { context };  
				   Constructor con = c2.getConstructor(paramTypes); 
				   Object obj=   con.newInstance(params);
				     Method method2 = c2.getMethod("setViewData",Luadata.class);
				     //调用printAddress方法，其中HK是为方法传递一个参数值
				     method2.invoke(obj,cd);
				     addChild((BaseView) obj);
				    
			   }catch (Exception e)
			   {
				   e.printStackTrace();
			   }
			   
			  //child.setViewData(cd);
			   
			  
			 
		   }
	   }
	   
   }

   public void removeAllView(BaseView v)
   {
		for(int i=0;i<v.getChildList(v).size();i++)
		{
			BaseView obj = (BaseView)( v.getChildList(v).get(i));
			removeAllView(obj);

			
		}
		this.removeAllViews();
		v.removeAllViews();
		
		Log.i("jjj", "remove views "+ v.getClass().getName());
		
   }
	@Override
	public void setContentView(BaseView v) {
		  // viewmodel.setAttrs(temp.getAttrs());
		   String strr = new String();
			for (Object key : v.getViewModel().hm.keySet()) {
				
				   String  value = (String) v.getViewModel().hm.get(key);
				   strr= strr+ " "+key+":"+value;
				}
			
        WidgetController.setViewAttrs(v);
        if(!v.getChildList(v).isEmpty())
		{
			for(int i=0;i<v.getChildList(v).size();i++)
			{
				BaseView obj = (BaseView)( v.getChildList(v).get(i));
				obj.setContentView(obj);
				
				BaseView s =  (BaseView) v.getChildList(v).get(i);
				Log.i("jjj", "v.getChildViewModel().."+i+": "+s);
				//this.removeAllViews();
				 this.addView( s);
			}
		}
		
	
	}
	
	public String getqName() {
		// TODO Auto-generated method stub
		return new String ("View");
	}
	

	
	public List getChildList(BaseView v) {
		// TODO Auto-generated method stub
		return subitems;
	}
	@Override
	public BaseView getParentView() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void SetParentView(BaseView v) {
		
	}



}
