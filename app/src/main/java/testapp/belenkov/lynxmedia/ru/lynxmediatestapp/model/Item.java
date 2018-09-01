package testapp.belenkov.lynxmedia.ru.lynxmediatestapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item{

	@SerializedName("events")
	private List<EventsItem> events;

	public void setEvents(List<EventsItem> events){
		this.events = events;
	}

	public List<EventsItem> getEvents(){
		return events;
	}

	@Override
 	public String toString(){
		return 
			"Item{" + 
			"events = '" + events + '\'' + 
			"}";
		}
}