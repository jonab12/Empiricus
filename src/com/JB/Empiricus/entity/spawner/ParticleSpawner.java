package com.JB.Empiricus.entity.spawner;

import com.JB.Empiricus.entity.particle.Particle;
import com.JB.Empiricus.entity.spawner.Spawner.Type;
import com.JB.Empiricus.level.Level;

public class ParticleSpawner extends Spawner{

	private int life;
	
	public ParticleSpawner(int x, int y, int amount,int life, Level level)
	{
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		
		for (int i = 0; i < amount; i++){
			level.add(new Particle(x,y, life));
		}
		
	}

	

}
