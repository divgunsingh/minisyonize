package com.Health;

public class Health {
	int health;
	
	public Health(int Health){
		health = Health;
	}
	
	public boolean isDead() {
		 return health<=0;
	}
	public void Damage(int amount) {
		 health = health-amount;
		 
	 }
	
	public void Heal(int Heal){
		health=health+Heal;	
	}		
}

