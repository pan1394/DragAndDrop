package com.linkage.dnd.parser;

import com.linkage.dnd.controls.ComponentMeta;

public interface Parser<E> {

	public ComponentMeta parse(E e);
}
