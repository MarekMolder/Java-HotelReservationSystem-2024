
  package ee.taltech.iti0202.idcode;

  import java.util.ArrayList;
  import java.util.Arrays;
  import java.util.Objects;

  public class IdCode {
  
      private final String idCodeValue;

      enum Gender {
          MALE, FEMALE
      }

      /**
       * Method returns the id code.
       *
       * @return id code.
       */
      public String getIdCodeValue() {
          return idCodeValue;
      }
    
      public IdCode(String idCodeValue) {
          this.idCodeValue = idCodeValue;
      }

      /**
       * Check if the id code is valid or not.
       *
       * @return boolean describing whether or not the id code was correct.
       */
      public boolean isCorrect() {
          return isGenderNumberCorrect() && isYearNumberCorrect() && isMonthNumberCorrect()
                  && isDayNumberCorrect() && isControlNumberCorrect();
      }


      /**
       * Get all information about id code.
       * 
       * @return String containing information.
       */
      public String getInformation() {
          return String.format("This is a %s born on %s.%s.%s in %s", getGender(), idCodeValue.substring(5,7),
                  idCodeValue.substring(3,5), getFullYear(), getBirthPlace());
      }

      /**
       * Get gender enum.
       * 
       * @return enum describing person's gender
       */
      public Gender getGender() {
          // firstnumber = self.id_code_value[0]
          char firstNumber = this.idCodeValue.charAt(0);
          if (firstNumber == '1' || firstNumber == '3' || firstNumber == '5') {
              return Gender.MALE;
          } else if (firstNumber == '2' || firstNumber == '4' || firstNumber == '6') {
              return Gender.FEMALE;
          }
          return null;
      }

      /**
       * Get person's birth location.
       * 
       * @return String with the person's birth place.
       */
      public String getBirthPlace() {
          String cityCode = this.idCodeValue.substring(7,10);
          if (1 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 10) {
              return "Kuressaare";
          } else if (11 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 20) {
              return "Tartu";
          } else if (21 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 220) {
              return "Tallinn";
          } else if (221 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 270) {
              return "Kohtla-Järve";
          }  else if (271 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 370) {
              return "Tartu";
          } else if (371 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 420) {
              return "Narva";
          } else if (421 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 470) {
              return "Pärnu";
          }  else if (471 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 490) {
              return "Tallinn";
          } else if (491 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 520) {
              return "Paide";
          }  else if (521 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 570) {
              return "Rakvere";
          } else if (571 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 600) {
              return "Valga";
          } else if (601 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 650) {
              return "Viljandi";
          } else if (651 <= Integer.parseInt(cityCode) && Integer.parseInt(cityCode) <= 710) {
              return "Võru";
          } else {
              return "unknown";
          }
      }

      /**
       * Get the year that the person was born in.
       * 
       * @return int with person's birth year.
       */
      public int getFullYear() {
          char firstNumber = this.idCodeValue.charAt(0);
          String birthYear = idCodeValue.substring(1,3);
          String firstHalf = "";
          if (firstNumber == '1' || firstNumber == '2'){
              firstHalf = "18";
          } else if (firstNumber == '3' || firstNumber == '4') {
              firstHalf = "19";
          } else if (firstNumber == '5' || firstNumber == '6') {
              firstHalf = "20";
          }
          return Integer.parseInt(firstHalf + birthYear);
      }
    
      /**
       * Check if gender number is correct.
       * 
       * @return boolean describing whether the gender number is correct.
       */
      private boolean isGenderNumberCorrect() {
          int firstNumber = Integer.parseInt(String.valueOf(idCodeValue.charAt(0)));
          if (0 < firstNumber && firstNumber < 7) {
              return true;
          } else {
              return false;
          }
      }

      /**
       * Check if the year number is correct.
       * 
       * @return boolean describing whether the year number is correct.
       */
      private boolean isYearNumberCorrect() {
          String birthYear = idCodeValue.substring(1,3);
          if (0 <= Integer.parseInt(birthYear) && Integer.parseInt(birthYear) <= 99) {
              return true;
          } else {
              return false;
          }
      }

      /**
       * Check if the month number is correct.
       * 
       * @return boolean describing whether the month number is correct.
       */
      private boolean isMonthNumberCorrect() {
          String birthYear = idCodeValue.substring(3,5);
          if (1 <= Integer.parseInt(birthYear) && Integer.parseInt(birthYear) <= 12) {
              return true;
          } else {
              return false;
          }
      }

      /**
       * Check if the day number is correct.
       * 
       * @return boolean describing whether the day number is correct.
       */
      private boolean isDayNumberCorrect() {
          int birthYear = getFullYear();
          ArrayList<String> month31 = new ArrayList<>(Arrays.asList("01", "03", "05", "07", "08", "10", "12"));
          ArrayList<String> month30 = new ArrayList<>(Arrays.asList("04", "06", "09", "11"));
          String birthMonth = idCodeValue.substring(3,5);
          int birthDay = Integer.parseInt(idCodeValue.substring(5,7));

          if (month31.contains(birthMonth) && (0 < birthDay && birthDay <= 31)) {
              return true;
          } else if (month30.contains(birthMonth) && (0 < birthDay && birthDay <= 30)) {
              return true;
          } else if (Objects.equals(birthMonth, "02")) {
              if (isLeapYear(birthYear) && (0 < birthDay && birthDay <= 29)) {
                  return true;
              } else if (!isLeapYear(birthYear) && (0 < birthDay && birthDay <= 28)) {
                  return true;
              }
          }
          return false;
      }

      /**
       * Check if the control number is correct.
       * 
       * @return boolean describing whether the control number is correct.
       */
      private boolean isControlNumberCorrect() {
          int multiplication = 0;
          for (int i = 1; i < idCodeValue.length() - 1; i++) {
                multiplication += i * Integer.parseInt(String.valueOf(idCodeValue.charAt(i - 1)));
          }
          multiplication += Integer.parseInt(String.valueOf(idCodeValue.charAt(9)));
          if (multiplication % 11 == 10) {
              for (int i = 3; i < 10; i++) {
                  multiplication = 0;
                  multiplication += i * Integer.parseInt(String.valueOf(idCodeValue.charAt(i - 3)));
              }
              for (int i = 8; i < 11; i++) {
                  multiplication += i - 7 * Integer.parseInt(String.valueOf(idCodeValue.charAt(i)));
              }
              if (multiplication % 11 == Integer.parseInt(String.valueOf(idCodeValue.charAt(10)))) {
                  return true;
              }
          } else {
              if (multiplication % 11 == Integer.parseInt(String.valueOf(idCodeValue.charAt(10)))) {
                  return true;
              }
          }
          return false;
      }

      /**
       * Check if the given year is a leap year.
       * 
       * @param fullYear
       * @return boolean describing whether the given year is a leap year.
       */
      private boolean isLeapYear(int fullYear) {
          if (fullYear % 400 == 0 || fullYear % 4 == 0) {
              return true;
          }
          return false;
      }

      /**
       * Run tests.
       * @param args info.
       */
      public static void main(String[] args) {
          IdCode validMaleIdCode = new IdCode("47605030299");
          System.out.println(validMaleIdCode.isCorrect());
          System.out.println(validMaleIdCode.getInformation());
          System.out.println(validMaleIdCode.getGender());
          System.out.println(validMaleIdCode.getBirthPlace());
          System.out.println(validMaleIdCode.getFullYear());
          System.out.println(validMaleIdCode.isGenderNumberCorrect());
          System.out.println(validMaleIdCode.isYearNumberCorrect());
          System.out.println(validMaleIdCode.isMonthNumberCorrect());
          System.out.println(validMaleIdCode.isDayNumberCorrect());
          System.out.println(validMaleIdCode.isControlNumberCorrect());
          System.out.println(validMaleIdCode.isLeapYear(validMaleIdCode.getFullYear()));
      }

  }
