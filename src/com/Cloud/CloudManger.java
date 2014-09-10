package com.Cloud;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.concurrent.CopyOnWriteArrayList;

	import android.view.VelocityTracker;

	import com.Enemy.Enemy;
	import com.threed.jpct.Logger;
	import com.threed.jpct.SimpleVector;

	public class CloudManger {

		public List<Cloud> clouds;

		public CloudManger() {
			clouds = new CopyOnWriteArrayList();

		}

		public void update(float elapsedTime) {

			
			for (Cloud e : clouds) {

				if (e.IsFlaggedForCleaning) {

					clouds.remove(e);

				} else {

					e.Update(elapsedTime);
				}

			}
		}

	}

