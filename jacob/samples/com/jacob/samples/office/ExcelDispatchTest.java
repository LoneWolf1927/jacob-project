package com.jacob.samples.office;

import com.jacob.com.*;
import com.jacob.activeX.*;

/**
 * Sample test program snagged out of a question on the sun discussion area.
 * Run options...
 * -Djava.library.path=d:/jacob/release -Dcom.jacob.autogc=false -Dcom.jacob.debug=true
 * @author joe
 *
 */
public class ExcelDispatchTest {
	 
	  public static void main(String[] args)
	  {
	    ComThread.InitSTA();
	 
	    ActiveXComponent xl = new ActiveXComponent("Excel.Application");
	    try {
	      System.out.println("version="+xl.getProperty("Version"));
	      System.out.println("version="+Dispatch.get(xl, "Version"));
	      Dispatch.put(xl, "Visible", new Variant(true));
	      Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
	      Dispatch workbook = Dispatch.get(workbooks,"Add").toDispatch();
	      Dispatch sheet = Dispatch.get(workbook,"ActiveSheet").toDispatch();
	      Dispatch a1 = Dispatch.invoke(sheet, "Range", Dispatch.Get,
	                                  new Object[] {"A1"},
	                                  new int[1]).toDispatch();
	      Dispatch a2 = Dispatch.invoke(sheet, "Range", Dispatch.Get,
	                                  new Object[] {"A2"},
	                                  new int[1]).toDispatch();
	      Dispatch.put(a1, "Value", "123.456");
	      Dispatch.put(a2, "Formula", "=A1*2");
	      System.out.println("a1 from excel:"+Dispatch.get(a1, "Value"));
	      System.out.println("a2 from excel:"+Dispatch.get(a2, "Value"));
	      Variant f = new Variant(false);
	      Dispatch.call(workbook, "Close", f);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      xl.invoke("Quit", new Variant[] {});
	      ComThread.Release();
	    }
	  }

}