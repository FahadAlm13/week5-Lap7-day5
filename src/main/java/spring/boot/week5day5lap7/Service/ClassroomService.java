package spring.boot.week5day5lap7.Service;

import org.springframework.stereotype.Service;
import spring.boot.week5day5lap7.Model.Classroom;

import java.util.ArrayList;

@Service
public class ClassroomService {

        ArrayList<Classroom> classrooms = new ArrayList<>();

        public ArrayList<Classroom> getClassrooms() {
                return classrooms;
        }

        public void addClassroom(Classroom classroom) {
                classrooms.add(classroom);
        }

        public boolean updateClassroom(Classroom classroom, int id) {
                for (int i = 0; i < classrooms.size(); i++) {
                        if (classrooms.get(i).getId() == id) {
                                classrooms.set(i, classroom);
                                return true;
                        }
                }
                return false;
        }

        public boolean deleteClassroom(int id) {
                return classrooms.removeIf(classroom -> classroom.getId() == id);
        }

        public Classroom getClassroomByName(String name) {
                for (Classroom classroom : classrooms) {
                        if (classroom.getName().equalsIgnoreCase(name)) {
                                return classroom;
                        }
                }
                return null;
        }

        public ArrayList<Classroom> getAvailableClassrooms() {
                ArrayList<Classroom> result = new ArrayList<>();
                for (Classroom classroom : classrooms) {
                        if (classroom.isAvailable()) {
                                result.add(classroom);
                        }
                }
                return result;
        }

        public boolean updateClassroomAvailability(int id, boolean isAvailable) {
                for (Classroom classroom : classrooms) {
                        if (classroom.getId() == id) {
                                classroom.setAvailable(isAvailable);
                                return true;
                        }
                }
                return false;
        }

        public ArrayList<Classroom> getClassroomsByCapacity(int capacity) {
                ArrayList<Classroom> result = new ArrayList<>();
                for (Classroom classroom : classrooms) {
                        if (classroom.getCapacity() >= capacity) {
                                result.add(classroom);
                        }
                }
                return result;
        }
}
