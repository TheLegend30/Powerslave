package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfEducation extends Ministry {

    private int teachers;
    private int teachersLimit;
    private int maximumTeachersLimit;
    private float teachersSalary;
    private float teachersSalaryNeed;

    private int teachersNeed;

    private int children;
    private int students;

    private int schools;
    private int schoolsLimit;
    private int schoolsNeed;

    private int colleges;
    private int collegesLimit;
    private int collegesNeed;

    private float literacy;
    private float degree;
    private float liberty;

    private MinistryOfEconomy economy;

    public MinistryOfEducation(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        name = "Ministry of Education and Science";

        this.economy = country.getMinistryOfEconomy();

        this.children = (int) (this.economy.getPopulation() - this.economy.getLabor_force() - country.getMinistryOfHealthcare().getPensioners());

        statsRandomizer();
    }

    public float getLiteracy() {
        return literacy;
    }

    public void setLiteracy(float literacy) {
        this.literacy = literacy;
    }

    public float getLiberty() {
        return liberty;
    }

    public void setLiberty(float liberty) {
        this.liberty = liberty;
    }

    @Override
    public String toString() {
        String string;
        string = "Literacy: " + String.format("%.2f", literacy * 100f) + " %" + "\n";
        string += "Degree: " + String.format("%.2f", degree * 100f) + " %" + "\n";
        string += "Liberty: " + String.format("%.2f", liberty * 100f) + " %" + "\n";
        string += "Teachers: " + teachers + "\n";
        string += "Teachers limit: " + teachersLimit + " (Maximum - " + maximumTeachersLimit + ")" + "\n";
        string += "Teachers salary: " + String.format("%.2f", teachersSalary) + " ƒ" + "\n";
        string += "Teachers salary need : " + String.format("%.2f", teachersSalaryNeed) + " ƒ" + "\n";
        string += "Children: " + children + "\n";
        string += "Students: " + students + "\n";
        string += "Schools: " + schools + "\n";
        string += "Colleges: " + colleges + "\n";
        string += "Schools need: " + schoolsNeed + "\n";
        string += "Colleges need: " + collegesNeed + "\n";
        string += "Teachers need: " + teachersNeed + "\n";
        return super.toString()+ string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        liberty = (float) (((2.5f / country.getMinistryOfInternalAffairs().getLevelOfSecurity()) * (4.5f / country.getMinistryOfJustice().getLevelOfJudgesLiberty())) * (degree * 25f) * (literacy / 25f) * (efficiency * 1.25f) * (country.getMinistryOfJustice().isDeathPenalty() ? 0.95 : 1.05));
        efficiency *= generalBudget / generalBudgetNeed;
        efficiency *= ((float) (schools + colleges) / (float) (schoolsNeed + collegesNeed));
        efficiency *= ((float) teachers / (float) teachersNeed);
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();

        Random random = new Random();

        float modifierTeachers = 0f;
        float modifierTeachersSalary = 0f;

        float modifierStudents = 0f;

        float modifierSchools = 0f;
        float modifierColleges = 0f;

        float modifierLiteracy = 0f;
        float modifierDegree = 0f;

        switch (country.getContinent()) {
            case EY:
                modifierTeachers = 0.013f;
                modifierTeachersSalary = 0.5f;

                modifierStudents = 0.03f;

                modifierSchools = 0.8f;
                modifierColleges = 0.65f;

                modifierLiteracy = 0.8f;
                modifierDegree = 0.08f;
                break;
            case NY:
                modifierTeachers = 0.017f;
                modifierTeachersSalary = 0.58f;

                modifierStudents = 0.038f;

                modifierSchools = 0.9f;
                modifierColleges = 0.75f;

                modifierLiteracy = 0.9f;
                modifierDegree = 0.11f;
                break;
            case SY:
                modifierTeachers = 0.01f;
                modifierTeachersSalary = 0.45f;

                modifierStudents = 0.025f;

                modifierSchools = 0.75f;
                modifierColleges = 0.6f;

                modifierLiteracy = 0.65f;
                modifierDegree = 0.07f;
                break;
            case WY:
                modifierTeachers = 0.02f;
                modifierTeachersSalary = 0.64f;

                modifierStudents = 0.04f;

                modifierSchools = 0.92f;
                modifierColleges = 0.8f;

                modifierLiteracy = 0.95f;
                modifierDegree = 0.14f;
                break;
            case IB:
                modifierTeachers = 0.008f;
                modifierTeachersSalary = 0.4f;

                modifierStudents = 0.022f;

                modifierSchools = 0.6f;
                modifierColleges = 0.45f;

                modifierLiteracy = 0.5f;
                modifierDegree = 0.025f;
                break;
            case OB:
                modifierTeachers = 0.009f;
                modifierTeachersSalary = 0.38f;

                modifierStudents = 0.02f;

                modifierSchools = 0.55f;
                modifierColleges = 0.4f;

                modifierLiteracy = 0.6f;
                modifierDegree = 0.02f;
                break;
            case CA:
                modifierTeachers = 0.0095f;
                modifierTeachersSalary = 0.35f;

                modifierStudents = 0.018f;

                modifierSchools = 0.5f;
                modifierColleges = 0.38f;

                modifierLiteracy = 0.4f;
                modifierDegree = 0.01f;
                break;
            case FA:
                modifierTeachers = 0.008f;
                modifierTeachersSalary = 0.3f;

                modifierStudents = 0.017f;

                modifierSchools = 0.45f;
                modifierColleges = 0.25f;

                modifierLiteracy = 0.42f;
                modifierDegree = 0.015f;
                break;
            case ME:
                modifierTeachers = 0.0075f;
                modifierTeachersSalary = 0.24f;

                modifierStudents = 0.015f;

                modifierSchools = 0.35f;
                modifierColleges = 0.15f;

                modifierLiteracy = 0.25f;
                modifierDegree = 0.01f;
                break;
            case GE:
                modifierTeachers = 0.018f;
                modifierTeachersSalary = 0.53f;

                modifierStudents = 0.033f;

                modifierSchools = 0.85f;
                modifierColleges = 0.75f;

                modifierLiteracy = 0.85f;
                modifierDegree = 0.09f;
                break;
        }

        teachersLimit = (int) (economy.getLabor_force() * (modifierTeachers + (random.nextFloat() * (0.003 - (-0.003)) + (-0.003))));
        teachers = teachersLimit;

        teachersSalary = (float) (economy.getGdpPerPerson() * (modifierTeachersSalary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        students = (int) (economy.getLabor_force() * (modifierStudents + (random.nextFloat() * (0.005 - (-0.005)) + (-0.005))));

        teachersSalaryNeed = (float) (economy.getGdpPerPerson() / 1.5);
        schoolsNeed = (int) Math.ceil(children / 500);
        collegesNeed = (int) Math.ceil(students / 1250);
        teachersNeed = (children + students) / 35;

        schoolsLimit = (int) (schoolsNeed * (modifierSchools + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        schools = schoolsLimit;

        collegesLimit = (int) (collegesNeed * (modifierColleges + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        colleges = collegesLimit;

        literacy = (float) (modifierLiteracy + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01)));
        degree = (float) (modifierDegree + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01)));
        maximumTeachersLimit = teachersNeed * 9;
    }
}
