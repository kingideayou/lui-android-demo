package cn.iolove.android.lui.utils;

import cn.iolove.android.lui.view.BaseView;
import cn.iolove.android.lui.view.BaseViewLayoutParams;
import android.graphics.Color;
import android.view.ViewGroup.MarginLayoutParams;  

  
/* 
 * ��ȡ�����ÿؼ���Ϣ 
 */  
public class WidgetController {  
  
    /* 
     * ��ȡ�ؼ��� 
     */  
    public static int getWidth(BaseView view)  
    {  
        int w = BaseView.MeasureSpec.makeMeasureSpec(0,BaseView.MeasureSpec.UNSPECIFIED);  
        int h = BaseView.MeasureSpec.makeMeasureSpec(0,BaseView.MeasureSpec.UNSPECIFIED);  
        view.measure(w, h);  
        return (view.getMeasuredWidth());         
    }  
    public static void setWidth(BaseView view,BaseViewLayoutParams lop)
    {
    	view.setLayoutParams(lop);
    }
    public static void setViewAttrs(BaseView v)
    {
		WidgetController.setLayout(v, v.getViewModel().getMarginleft(), v.getViewModel().getMargintop(),v.getViewModel().getWidth(),v.getViewModel().getHeight());
		WidgetController.setBackgroundColor(v,v.getViewModel().getBackgroundColor());
    }
    /* 
     * ��ȡ�ؼ��� 
     */  
    public static int getHeight(BaseView view)  
    {  
        int w = BaseView.MeasureSpec.makeMeasureSpec(0,BaseView.MeasureSpec.UNSPECIFIED);  
        int h = BaseView.MeasureSpec.makeMeasureSpec(0,BaseView.MeasureSpec.UNSPECIFIED);  
        view.measure(w, h);  
        return (view.getMeasuredHeight());         
    }  
      
    /* 
     * ���ÿؼ����ڵ�λ��X�����Ҳ��ı��ߣ� 
     * XΪ����λ�ã���ʱY���ܹ�0 
     */  

    /* 
     * ���ÿؼ����ڵ�λ��Y�����Ҳ��ı��ߣ� 
     * YΪ����λ�ã���ʱX���ܹ�0 
     */  
    public static void setBackgroundColor(BaseView view ,String p)
    {
    	view.setBackgroundColor(Color.parseColor(p));
    }

    /* 
     * ���ÿؼ����ڵ�λ��YY�����Ҳ��ı��ߣ� 
     * XYΪ����λ�� 
     */  
    public static void setLayout(BaseView view,int x,int y,int w ,int h)  
    {  
       view.layout(x, y, x+w, y+h);
       MarginLayoutParams margin=new MarginLayoutParams(w,h); 
       margin.setMargins(x,y, x+w, y+h); 
       BaseViewLayoutParams layoutParams = new BaseViewLayoutParams(margin); 
       view.setLayoutParams(layoutParams); 
    }  
}  