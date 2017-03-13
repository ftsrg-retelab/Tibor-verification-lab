package hu.bme.mit.train.system;

import java.util.Timer;
import java.util.TimerTask;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.sensor.TrainSensorImpl;
import hu.bme.mit.train.user.TrainUserImpl;

public class TrainSystem {

	private TrainController controller = new TrainControllerImpl();
	private TrainUser user = new TrainUserImpl(controller);
	private TrainSensor sensor = new TrainSensorImpl(controller, user);

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}
	
	public void runTrain(){
		TimerTask task = new ChangeSpeedTask();
		Timer timer = new Timer();
		timer.schedule(task, 1000,60000);
	}

	public class ChangeSpeedTask extends TimerTask{
		@Override
		public void run() {
			controller.followSpeed();
		}
	}
	
    /*public static void main(String[] args){
    	runTrain();
    }*/

}
