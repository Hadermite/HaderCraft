package se.wiklund.minecraft2d.listener;

import se.wiklund.minecraft2d.component.CheckBox;

public interface CheckBoxListener {
	
	public void onChangeValue(CheckBox box, boolean checked);
}
