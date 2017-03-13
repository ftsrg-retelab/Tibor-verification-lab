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
	private PeriodicChecker perChk = new PeriodicChecker(controller);

	public TrainController getController() {
		return controller;
	}

	public TrainSensor getSensor() {
		return sensor;
	}

	public TrainUser getUser() {
		return user;
	}
	
	public void run() {
		perChk.run();
	}

	public class PeriodicChecker extends Thread
	{
		
		private TrainController ctr;
		
		public PeriodicChecker(TrainController ctrl) {
			this.ctr = ctrl;
		}
		
	    @Override
	    public void run()
	    {
	        while(true) {
	           ctr.followSpeed();
	           try {
				Thread.sleep(5000);
	           } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	    }

	}
	
}
