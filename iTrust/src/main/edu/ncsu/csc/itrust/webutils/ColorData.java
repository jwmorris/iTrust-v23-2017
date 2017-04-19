/**
 * 
 */
package edu.ncsu.csc.itrust.webutils;

import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.model.DataBean;

/**
 * @author wyattmaxey
 */
public interface ColorData extends DataBean<ColorBean> {
	public ColorBean getColorBean( long pid ) throws DBException;
}
