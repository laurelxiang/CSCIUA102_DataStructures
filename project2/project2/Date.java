package project2;

public class Date implements Comparable<Date> {
    // initializing variables
    private int year;
    private int month;
    private int day;

    // initializing constructor with exception
    public Date(int year, int month, int day) throws IllegalArgumentException {
        // validating year, month, day are positive int
        if (year < 0) {
            throw new IllegalArgumentException("Invalid value for year. "
            + "Valid input is positive integer.");
        }
        if (month < 0) {
            throw new IllegalArgumentException("Invalid value for year. "
            + "Valid input is positive month.");
        }
        if (day < 0) {
            throw new IllegalArgumentException("Invalid value for day. "
            + "Valid input is positive integer.");
        }
        // validating month
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid value for month. "
            + "Valid range is 1-12.");
        }
        // validating days
        // determining leap year variation for day
        if (year % 4 == 0 && month == 2) {
            if (day < 1 || day > 29) {
            throw new IllegalArgumentException("Invalid value for day. "
            + "Valid range is 1-29 for February during leap years.");
            }
        }
        // determining nonleap year variation for day
        if (month == 2) {
            if (year % 4 != 0) {
                if (day < 1 || day > 28) {
                throw new IllegalArgumentException("Invalid value for day. "
                + "Valid range is 1-28 for February during non-leap years.");
                }
            }
            else if (year % 100 != 0) {
                if (day < 1 || day > 29) {
                throw new IllegalArgumentException("Invalid value for day. "
                + "Valid range is 1-28 for February during leap years.");
                }
            }
            else if (year % 400 != 0) {
                if (day < 1 || day > 28) {
                throw new IllegalArgumentException("Invalid value for day. "
                + "Valid range is 1-28 for February during non-leap years.");
                }
            }
            else {
                if (day < 1 || day > 29) {
                    throw new IllegalArgumentException("Invalid value for day. "
                    + "Valid range is 1-28 for February during leap years.");
                }
            }
        }

        // determining length of non-february months
        int monthtype = 0; //specifying if month contains 30 or 31 days
        int[] thirtyone = {1,3,5,7,8,10,12};
        int[] thirty = {4,6,9,11};
        for (int element : thirtyone) {
            if (element == month) {
                monthtype = 2;
            }
        }
        for (int element : thirty) {
            if (element == month) {
                monthtype = 1;
            }
        }

        // validing 30 day months (Apr, Jun, Sept, Nov) - 4,6,7,9
        if (monthtype == 1) {
            if (day < 1 || day > 30) {
            throw new IllegalArgumentException("Invalid value for day. "
            + "Valid range is 1-30 for %" + month + ".");
            }
        }
        // validing 31 day months (Jan, Mar, May, July, Aug, Oct, Dec) - 1,3,5,7,8,10,12
        if (monthtype == 2) {
            if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid value for day. "
            + "Valid range is 1-31 for " + month + ".");
            }
        }

        // initializing variables
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // getters and setters 
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) throws IllegalArgumentException {
        //validating year
        if (year < 0) {
            throw new IllegalArgumentException("Invalid value for year. "
            + "Valid input is positive integer.");
        }
        this.year = year;
    }

    public void setMonth(int month) throws IllegalArgumentException {
        //validating month
        if (month < 0) {
            throw new IllegalArgumentException("Invalid value for year. "
            + "Valid input is positive month.");
        }
        if (month < 1 && month > 12) {
            throw new IllegalArgumentException("Invalid value for month. "
            + "Valid range is 1-12.");
        }
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

	/**
	 * @returns the string representation of date
	 */
	@Override
    public String toString () {
        if (getDay() < 10) {
            if (getMonth() >= 10) { 
                return String.format("%d-%d-0%d", 
                    getYear(), getMonth(), getDay()); 
            }
            if (getMonth() < 10) { 
                return String.format("%d-0%d-0%d", 
                    getYear(), getMonth(), getDay()); 
            }
        }
        if (getDay() >= 10) {
            if (getMonth() >= 10) { 
                return String.format("%d-%d-%d", 
                    getYear(), getMonth(), getDay()); 
            }
            if (getMonth() < 10) { 
                return String.format("%d-0%d-%d", 
                    getYear(), getMonth(), getDay()); 
            }
        }
        return ""; 
	}

	/**
	 * Indicates whether some object obj is "equal to" this one, where objects contain dates
     * returns 0 if objects are "equal"
     * returns 1 if this object is greater than compared object
     * returns -1 if this object is smaller than compared object
	 */
    @Override
    public int compareTo(Date o) throws IllegalArgumentException {
        if (this.getYear() > o.getYear()) {
            return 1;
        }
        else if (this.getYear() < o.getYear()) {
            return -1;
        }
        else if (this.getYear() == o.getYear()) {
            if (this.getMonth() > o.getMonth()) {
                return 1;
            }
            else if (this.getMonth() < o.getMonth()) {
                return -1;
            }
            else if (this.getMonth() == o.getMonth()) {
                if (this.getDay() > o.getDay()) {
                    return 1;
                }
                else if (this.getDay() < o.getDay()) {
                    return -1;
                }
                else if (this.getDay() == o.getDay()) {
                    return 0;
                }
            }
        } 
        return 2; //comparison failed
    }
    //overriding equals from object class
    @Override
    public boolean equals(Object o) throws IllegalArgumentException {
        if (this.getYear() == ((Date) o).getYear()) {
            if (this.getMonth() == ((Date) o).getMonth()) {
                if (this.getDay() == ((Date) o).getDay()) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}