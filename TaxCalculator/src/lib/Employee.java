package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class FamilyInfo {
    private String spouseName;
    private String spouseIdNumber;
    private List<String> childNames = new LinkedList<>();
    private List<String> childIdNumbers = new LinkedList<>();

    public void setSpouse(String name, String id) {
        this.spouseName = name;
        this.spouseIdNumber = id;
    }

    public void addChild(String name, String id) {
        childNames.add(name);
        childIdNumbers.add(id);
    }

    public boolean isMarried() {
        return spouseIdNumber != null && !spouseIdNumber.isEmpty();
    }

    public int getNumberOfChildren() {
        return familyInfo.getNumberOfChildren();
    }
}

public enum Gender {
    MALE,
    FEMALE
}


public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private Gender gender;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private FamilyInfo familyInfo = new FamilyInfo();
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 3000000;
		}else if (grade == 2) {
			monthlySalary = 5000000;
		}else if (grade == 3) {
			monthlySalary = 7000000;
		}
		if (isForeigner){
			monthlySalary=(int) monthlySalary*1.5
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String name, String id) {
		familyInfo.setSpouse(name, id);
	}

	public void addChild(String name, String id) {
		familyInfo.addChild(name, id);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		TaxData taxData = new TaxData(
			monthlySalary,
			otherMonthlyIncome,
			monthWorkingInYear,
			annualDeductible,
			familyInfo.isMarried(),
			childIdNumbers.size()
		);

		return TaxFunction.calculateTax(taxData);

	}
}

//Test