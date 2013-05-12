import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader{

	private File file;
	Participant participant = new Participant();
	private boolean vehicleFlag = false;
	private boolean timeFlag = false;
	private boolean resourceFlag = false;
	private boolean error = false;

	public Reader(File file) {

		this.file = file;

	}

	public Reader readIn() throws FileNotFoundException{

		Scanner s = new Scanner(new FileReader(file));

		try {
			while (s.hasNextLine()){
				processLine(s.nextLine());
			}
		} catch (ArrayIndexOutOfBoundsException e){
			error = true;
			Main.print("The file (" + file.getName() + ") is not correctly formated. Is it a trial file? Skipping this data.");
		} catch (Exception e){
			error = true;
			e.printStackTrace();
			Main.print("Something has gone wrong! Skipping this file.");
		} finally {
			s.close();
		}

		return this;
	}

	private void processLine(String line){
		
		if (line.equals("") || line.startsWith("Time")){
			vehicleFlag = false;
			timeFlag = false;
			resourceFlag = false;
		}else if (line.startsWith("Number")){
			if (line.startsWith("Number\tIdle")) vehicleFlag = true;
			if (line.startsWith("Number\tManual")) timeFlag = true;
		} else if (line.startsWith("Participant Id")){
			participant.number = Integer.parseInt(line.split("\t")[1]);
		} else if (line.startsWith("Overall Performance")){
			participant.performance = line.split("\t")[1];
		} else if (line.startsWith("Generations")){
			participant.generations = line.split("\t")[1];
		} else if (line.startsWith("Forest")){
			participant.forest = line.split("\t")[1];
		} else if (line.startsWith("Pasture")){
			participant.pasture = line.split("\t")[1];
		} else if (line.startsWith("Clearing")){
			participant.clearing = line.split("\t")[1];
		} else if (line.startsWith("House")){
			participant.house = line.split("\t")[1];
		} else if (line.startsWith("Total Consumable")){
			participant.totalConsumableLandscape = line.split("\t")[1];
		} else if (line.startsWith("Commands Made")){
			participant.commandsMade = line.split("\t")[1];
		} else if (vehicleFlag){

			String[] parts = line.split("\t");
			int number = Integer.parseInt(parts[0]);
			int idle = Integer.parseInt(parts[1]);
			int moving = Integer.parseInt(parts[2]);
			int fighting = Integer.parseInt(parts[3]);
			int filling = Integer.parseInt(parts[4]);
			int treating = Integer.parseInt(parts[5]);

			Participant.Vehicle vehicle = participant.new Vehicle(
					number,
					idle,
					moving,
					fighting,
					filling,
					treating
					);

			participant.vehicles[number-1] = vehicle;

		} else if (timeFlag){
			if (line.startsWith("Resources Used")){
				timeFlag = false;
				resourceFlag = true;
				return;
			}
			String[] parts = line.split("\t");
			int number = Integer.parseInt(parts[0]);
			int manual = Integer.parseInt(parts[1]);
			int auto = Integer.parseInt(parts[2]);
			
			participant.manual[number-1] = manual;
			participant.auto[number-1] = auto;
		} else if (resourceFlag){
			String[] parts = line.split("\t");
			int number = Integer.parseInt(parts[0]);
			int resources = Integer.parseInt(parts[3]);
			participant.resources[number-1] = resources;
		}

	}

	public String output(){
		if (error)
			return "";
		else
			return participant.toString();
	}

}
