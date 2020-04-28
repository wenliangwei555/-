package com.msghub.msghub.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import com.baisee.constants.AppConstants;

/**
 * 
* @ClassName: ParamUtils 
* @Description: TODO
* @author yc 
* @date 2015年7月24日 下午5:19:24
 */
public class ParamUtils {
	
	public ParamUtils() {
    }

    /**
     * 从request中得到数值的函数，如果为null，返回“”
     * 
     * @param request
     *            HttpServletRequest对象
     * @param param
     *            参数
     * @return 返回提交传递的值
     */
    public static String getParameter(HttpServletRequest request, String param) {
        String str = request.getParameter(param);
        if (str != null) {
            return str.trim();
        } else {
            return "";
        }
    }

    /**
     * 从Session中取到变量的值，如果为null，则返回空
     * 
     * @param session
     *            HttpSession对象
     * @param sessionName
     *            Session名
     * @return 返回Session值
     */
    public static String getSession(HttpSession session, String sessionName) {
        String retStr = "";
        try {
            retStr = session.getAttribute(sessionName).toString();
        } catch (Exception e) {
            retStr = "";
        }
        return retStr;
    }

    /**
     * 从request中得到数值的函数，如果为NULL的话，则返回默认值
     * 
     * @param request
     *            HttpServletRequest对象
     * @param paramName
     *            参数名称
     * @param defaultString
     *            默认值
     * @return 参数对应的值或者默认值（如果为NULL）
     */
    public static String getStringParameter(HttpServletRequest request,
            String paramName, String defaultString) {
        String temp = request.getParameter(paramName);
        if ((temp == null) || (temp.compareTo("") == 0)) {
            return defaultString;
        } else {
            return temp;
        }
    }

    /**
     * Gets a parameter as a int.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param paramName
     *            The name of the parameter you want to get
     * @return The int value of the parameter specified or the default value if
     *         the parameter is not found.
     */
    public static int getIntParameter(HttpServletRequest request,
            String paramName, int defaultNum) {
        String temp = request.getParameter(paramName);
        if (temp != null && !"".equals(temp)) {
            int num = defaultNum;
            try {
                num = Integer.parseInt(temp);
            } catch (Exception ignored) {
            }
            return num;
        } else {
            return defaultNum;
        }
    }

    /**
     * Gets a parameter as a string.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the parameter you want to get
     * @param emptyStringsOK
     *            Return the parameter values even if it is an empty string.
     * @return The value of the parameter or null if the parameter was not
     *         found.
     */
    public static String getParameter(HttpServletRequest request, String name,
                                      boolean emptyStringsOK) {
        String temp = request.getParameter(name);
        if (temp != null) {
            if ("".equals(temp) && !emptyStringsOK) {
                return null;
            } else {
                return temp;
            }
        } else {
            return null;
        }
    }

    /**
     * Gets a parameter as a boolean.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the parameter you want to get
     * @return True if the value of the parameter was "true", false otherwise.
     */
    public static boolean getBooleanParameter(HttpServletRequest request,
            String name) {
        return getBooleanParameter(request, name, false);
    }

    /**
     * Gets a parameter as a boolean.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the parameter you want to get
     * @return True if the value of the parameter was "true", false otherwise.
     */
    public static boolean getBooleanParameter(HttpServletRequest request,
            String name, boolean defaultVal) {
        String temp = request.getParameter(name);
        if ("true".equals(temp) || "on".equals(temp)) {
            return true;
        } else if ("false".equals(temp) || "off".equals(temp)) {
            return false;
        } else {
            return defaultVal;
        }
    }

    /**
     * Gets a list of int parameters.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the parameter you want to get
     * @param defaultNum
     *            The default value of a parameter, if the parameter can't be
     *            converted into an int.
     */
    public static int[] getIntParameters(HttpServletRequest request,
            String name, int defaultNum) {
        String[] paramValues = request.getParameterValues(name);
        if (paramValues == null) {
            return null;
        }
        if (paramValues.length < 1) {
            return new int[0];
        }
        int[] values = new int[paramValues.length];
        for (int i = 0; i < paramValues.length; i++) {
            try {
                values[i] = Integer.parseInt(paramValues[i]);
            } catch (Exception e) {
                values[i] = defaultNum;
            }
        }
        return values;
    }

    /**
     * 取得一个字符串数组
     * 
     * @param request
     * @param name
     * @return
     */
    public static String[] getParameters(HttpServletRequest request, String name) {

        String[] paramValues = request.getParameterValues(name);
        if (paramValues == null) {
            return null;
        }
        if (paramValues.length < 1) {
            return new String[0];
        }
        return paramValues;

    }

    /**
     * Gets a parameter as a double.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the parameter you want to get
     * @return The double value of the parameter specified or the default value
     *         if the parameter is not found.
     */
    public static double getDoubleParameter(HttpServletRequest request,
            String name, double defaultNum) {
        String temp = request.getParameter(name);
        if (temp != null && !"".equals(temp)) {
            double num = defaultNum;
            try {
                num = Double.parseDouble(temp);
            } catch (Exception ignored) {
            }
            return num;
        } else {
            return defaultNum;
        }
    }

    /**
     * Gets a parameter as a long.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the parameter you want to get
     * @return The long value of the parameter specified or the default value if
     *         the parameter is not found.
     */
    public static long getLongParameter(HttpServletRequest request,
            String name, long defaultNum) {
        String temp = request.getParameter(name);
        if (temp != null && !"".equals(temp)) {
            long num = defaultNum;
            try {
                num = Long.parseLong(temp);
            } catch (Exception ignored) {
            }
            return num;
        } else {
            return defaultNum;
        }
    }

    /**
     * Gets a list of long parameters.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the parameter you want to get
     * @param defaultNum
     *            The default value of a parameter, if the parameter can't be
     *            converted into a long.
     */
    public static long[] getLongParameters(HttpServletRequest request,
            String name, long defaultNum) {
        String[] paramValues = request.getParameterValues(name);
        if (paramValues == null) {
            return null;
        }
        if (paramValues.length < 1) {
            return new long[0];
        }
        long[] values = new long[paramValues.length];
        for (int i = 0; i < paramValues.length; i++) {
            try {
                values[i] = Long.parseLong(paramValues[i]);
            } catch (Exception e) {
                values[i] = defaultNum;
            }
        }
        return values;
    }

    /**
     * Gets a parameter as a string.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the parameter you want to get
     * @return The value of the parameter or null if the parameter was not found
     *         or if the parameter is a zero-length string.
     */
    public static String getAttribute(HttpServletRequest request, String name) {
        return getAttribute(request, name, false);
    }

    /**
     * Gets a parameter as a string.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the parameter you want to get
     * @param emptyStringsOK
     *            Return the parameter values even if it is an empty string.
     * @return The value of the parameter or null if the parameter was not
     *         found.
     */
    public static String getAttribute(HttpServletRequest request, String name,
                                      boolean emptyStringsOK) {
        String temp = (String) request.getAttribute(name);
        if (temp != null) {
            if ("".equals(temp) && !emptyStringsOK) {
                return null;
            } else {
                return temp;
            }
        } else {
            return null;
        }
    }

    /**
     * Gets an attribute as a boolean.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the attribute you want to get
     * @return True if the value of the attribute is "true", false otherwise.
     */
    public static boolean getBooleanAttribute(HttpServletRequest request,
            String name) {
        String temp = (String) request.getAttribute(name);
        if (temp != null && "true".equals(temp)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets an attribute as a int.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the attribute you want to get
     * @return The int value of the attribute or the default value if the
     *         attribute is not found or is a zero length string.
     */
    public static int getIntAttribute(HttpServletRequest request, String name,
                                      int defaultNum) {
        String temp = (String) request.getAttribute(name);
        if (temp != null && !"".equals(temp)) {
            int num = defaultNum;
            try {
                num = Integer.parseInt(temp);
            } catch (Exception ignored) {
            }
            return num;
        } else {
            return defaultNum;
        }
    }

    /**
     * Gets an attribute as a long.
     * 
     * @param request
     *            The HttpServletRequest object, known as "request" in a JSP
     *            page.
     * @param name
     *            The name of the attribute you want to get
     * @return The long value of the attribute or the default value if the
     *         attribute is not found or is a zero length string.
     */
    public static long getLongAttribute(HttpServletRequest request,
            String name, long defaultNum) {
        String temp = (String) request.getAttribute(name);
        if (temp != null && !"".equals(temp)) {
            long num = defaultNum;
            try {
                num = Long.parseLong(temp);
            } catch (Exception ignored) {
            }
            return num;
        } else {
            return defaultNum;
        }
    }
   /**
    * 
   * @Title: getPageNumParameters 
   * @Description: TODO 获取分页的参数
   * @param @param request
   * @param @param param
   * @param @return    设定文件 
   * @return Integer    返回类型 
   * @throws
    */
    public static Integer getPageNumParameters(HttpServletRequest request){
    	 String str = getParameter(request, "pageNum");
         if (str != null&&!"".equals(str)) {
             return Integer.valueOf(str);
         } else {
             return 1;
         }
    }
    /**
     * 获取分页的单页显示的数据量
     * @param request
     * @return
     */
   /* public static Integer getPageSizeParameters(HttpServletRequest request){
    	String str = getParameter(request, "pageSize");
        if (str != null&&!str.equals("") && !StringUtils.equals("0", str)) {
            return Integer.valueOf(str);
        } else {
            return AppConstants.PAGE_SIZE;
        }
   }*/
}