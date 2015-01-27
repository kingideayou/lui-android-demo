package cn.iolove.android.lui.Context;

import java.util.Stack;

import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import cn.iolove.android.lui.page.Page;
import cn.iolove.android.lui.utils.Utils;

public class RuntimeContext implements PageControl{
	private static Stack<Page>  StackPage = new Stack<Page>() ;
	private static RuntimeContext rc = new RuntimeContext();
	private Context context;
	private RuntimeContext(){};
	public static RuntimeContext getInstance(){ return rc;}

	public void setContext(Context co)
	{
		context =co;
	}
	public void switchpage(String pagename) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pushpage(String pagename) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void poppage() {
	 if(StackPage.isEmpty())
	 {
		 return;
	 }
		
	}
	public void readLuaFile(String name,LuaState mLuaState)
	{
			
			mLuaState.openLibs();
			try {
	
				
				int error = mLuaState.LdoString(Utils.loadAssetsString("framework/ui.lua",context)+Utils.loadAssetsString("lua/main.lua",context));
				if(error!=0)
				{
					new AlertDialog.Builder(context).setTitle("LUA����").setMessage(mLuaState.toString(-1)).setPositiveButton("ȷ��", null).show();
					return;
		
				}
			   mLuaState.getField(LuaState.LUA_GLOBALSINDEX, "onCreated");
			  // mLuaState.pushNumber(1);
				mLuaState.call(0, 1);
				mLuaState.setField(LuaState.LUA_GLOBALSINDEX, "resulttable");
				
			  mLuaState.getGlobal("resulttable");
			  LuaObject obj = mLuaState.getLuaObject("resulttable");
				mLuaState.pushNil();
			
				while(mLuaState.next(-2)!=0)
				{
					
					Log.i("xxx", "value: "+mLuaState.toString(-1));
					Log.i("xxx", "key: "+mLuaState.toString(-2));
					mLuaState.pop(1);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.print("��ʧ��");
				e.printStackTrace();
			}		
	}
}