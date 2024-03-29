package com.JB.Empiricus.entity.spawner;

import com.JB.Empiricus.entity.Entity;
import com.JB.Empiricus.entity.particle.Particle;
import com.JB.Empiricus.level.Level;

public abstract class Spawner extends Entity{

	
	public enum Type {
		MOB, PARTICLE;
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
		
	}

}
