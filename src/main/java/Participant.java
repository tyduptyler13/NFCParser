
public class Participant{
	
	int number;
	String performance;
	String generations;
	String forest;
	String pasture;
	String clearing;
	String house;
	String totalConsumableLandscape;
	String commandsMade;
	
	Vehicle[] vehicles = new Vehicle[7];
	
	public class Vehicle{
		int vehicleNumber;
		int idle;
		int moving;
		int fighting;
		int filling;
		int treating;
		
		public Vehicle(int number, int idle, int moving, int fighting, int filling, int treating){
			this.vehicleNumber = number;
			this.idle = idle;
			this.moving = moving;
			this.fighting = fighting;
			this.filling = filling;
			this.treating = treating;
		}
		
		@Override
		public String toString(){
			return vehicleNumber + "\t" + idle + "\t" + moving + "\t" +
					fighting + "\t" + filling + "\t" + treating;
		}
	}
	
	int[] manual = new int[7];
	int[] auto = new int[7];
	int[] resources = new int[7];
	
	@Override
	public String toString(){
		
		String result = "";
		
		for (int i = 0; i < vehicles.length; ++i){
			
			if (vehicles[i] == null){ continue; }//Skip any lines that weren't setup.
			
			result += number + "\t\t" + performance + "\t" + generations + "\t" +
					forest + "\t" + pasture + "\t" + clearing + "\t" + house + "\t" +
					totalConsumableLandscape + "\t" + commandsMade + "\t" +
					vehicles[i].toString() + "\t" + manual[i] + "\t" + auto[i] + "\t" + 
					resources[i] + "\r\n";
			
		}
		
		return result;
	}
	
}
