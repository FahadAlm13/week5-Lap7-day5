package spring.boot.week5day5lap7.Service;

import org.springframework.stereotype.Service;
import spring.boot.week5day5lap7.Model.Instructor;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class InstructorService {

    ArrayList<Instructor> instructors = new ArrayList<>();

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public boolean updateInstructor(Instructor instructor, int id) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getId() == id) {
                instructors.set(i, instructor);
                return true;
            }
        }
        return false;
    }

    public boolean deleteInstructor(int id) {
        return instructors.removeIf(instructor -> instructor.getId() == id);
    }

    public boolean updateInstructorEmail(String currentEmail, String newEmail) {
        for (Instructor instructor : instructors) {
            if (instructor.getEmail().equalsIgnoreCase(currentEmail)) {
                instructor.setEmail(newEmail);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Instructor> getInstructorsByExperience(int yearsOfExperience) {
        ArrayList<Instructor> result = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.getYearsOfExperience() >= yearsOfExperience) {
                result.add(instructor);
            }
        }
        return result;
    }

    public ArrayList<Instructor> getAllInstructorsSortedByExperience() {
        ArrayList<Instructor> sortedInstructors = new ArrayList<>(instructors);
        sortedInstructors.sort(Comparator.comparingInt(Instructor::getYearsOfExperience).reversed());
        return sortedInstructors;
    }

    public boolean updateInstructorPhoneNumber(String email, String newPhoneNumber) {
        for (Instructor instructor : instructors) {
            if (instructor.getEmail().equalsIgnoreCase(email)) {
                instructor.setPhoneNumber(newPhoneNumber);
                return true;
            }
        }
        return false;
    }
}

