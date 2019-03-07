package com.linkage.dnd.controls.swing.factory;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import com.linkage.dnd.controls.Builder;
import com.linkage.dnd.controls.ComponentType;
import com.linkage.dnd.controls.swing.JButtonBuilder;
import com.linkage.dnd.controls.swing.JLabelBuilder;

public class ComponentFactory {
	
    private static ComponentFactory factory = new ComponentFactory();
    
    private static Map<ComponentType, Builder<JComponent>> strategyMap = new HashMap<ComponentType, Builder<JComponent>>();
    
    static{
       strategyMap.put(ComponentType.BUTTON, new JButtonBuilder());
       strategyMap.put(ComponentType.LABEL, new JLabelBuilder()); 
    }
    
    private ComponentFactory(){
    }
    
    public static ComponentFactory getInstance(){
        return factory;
    }
    
    public Builder<JComponent> creator(ComponentType type){
       return strategyMap.get(type);
    } 
 
}
