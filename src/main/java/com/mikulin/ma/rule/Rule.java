package com.mikulin.ma.rule;

/**
 * Rule interface.
 *
 */
public interface Rule {
	/**
	 * Applies rule to the specified input.
	 * 
	 * @param lines
	 */
	void apply(String[] lines);
}
