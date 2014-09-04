package com.Health;

public class Health {
	int health;
	
	public Health(int Health){
		health = Health;
	}
	
	private boolean isDead() {
		 return true;
	}
	 void Damage(int amount) {
		 health = health-amount;
		 
	 }
	
	public void Heal(int Heal){
		health=health+Heal;	
	}		
}

