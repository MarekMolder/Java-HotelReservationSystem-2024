
  package ee.taltech.iti0202.idcode;

  import java.util.ArrayList;
  import java.util.Arrays;
  import java.util.Objects;

  public class IdCode {
      public static final int BIRTHPALCE_KURESSAARE_START = 1;
      public static final int  = 11;
      public static final int BIRTHPALCE_TARTU_START = 11;
      public static final int BIRTHPALCE_TALLINN_START = 21;
      public static final int BIRTHPALCE_KOHTLA_JARVE_START = 221;
      public static final int BIRTHPALCE_TARTU2_START = 271;
      public static final int BIRTHPALCE_TARTU_2_START = BIRTHPALCE_TARTU2_START;
      public static final int BIRTHPLACE_NARVA_START = 371;
      public static final int BIRTHPLACE_TALLINN2_START = 471;
      public static final int BIRTHPLACE_TALLINN_2_START = BIRTHPLACE_TALLINN2_START;
      public static final int BIRTHPLACE_PAIDE_START = 491;
      public static final int BIRTHPLACE_RAKVERE_START = 521;
      public static final int BIRTHPLACE_VALGA_START = 571;
      public static final int BIRTHPLACE_VILJANDI_START = 601;
      public static final int BIRTHPALCE_TALLINN_FINIS = 220;
      public static final int BIRTHPALCE_KOHTLA_JARVE_FINIS = 270;
      public static final int BIRTHPALCE_TARTU_2_FINIS = 370;
      public static final int BIRTHPLACE_NARVA_FINIS = 420;
      public static final int BIRTHPLACE_PARNU_FINIS = 470;
      public static final int BIRTHPLACE_TALLINN_2_FINIS = 490;
      public static final int BIRTHPLACE_PAIDE_FINIS = 520;
      public static final int BIRTHPLACE_RAKVERE_FINIS = 570;
      public static final int BIRTHPLACE_VALGA_FINIS = 600;
      public static final int BIRTHPLACE_VILJANDI_FINIS = 650;
      public static final int BIRTHPALCE_KURESSAARE_FINIS = 10;
      public static final int BIRTHPALCE_TARTU_FINIS = 20;
      public static final int BIRTHPALCE_PARNU_START = 421;
      public static final int BIRTHPALCE_VORU_START = 651;
      public static final int BIRTHPALCE_VORU_FINIS = 710;
      public static final int BIRTYEAR_LAST_NUMBER = 99;
      public static final int MONTHS_MAX = 12;
      public static final int MONTH_MAX_DAY = 31;
      public static final int MONTH_MAX_DAY_1 = 30;
      public static final int FEBRUARY_MAX_DAY = 29;
      public static final int FEBRUARY_MAX_DAY_1 = 28;
      public static final int LEAP_YEAR_DIVIDE_NUMBER = 400;
      public static final int CONTROL_NUMBER_CONSTANT = 11;
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
          /*
          if (!isCorrect()) {
              throw new IllegalArgumentException();
          }
           */
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
          return String.format("This is a %s born on %s.%s.%s in %s", getGender(), idCodeValue.substring(5, 7),
                  idCodeValue.substring(3, 5), getFullYear(), getBirthPlace());
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
          String cityCode = this.idCodeValue.substring(7, 10);
          if (BIRTHPALCE_KURESSAARE_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPALCE_KURESSAARE_FINIS) {
              return "Kuressaare";
          } else if (BIRTHPALCE_TARTU_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPALCE_TARTU_FINIS) {
              return "Tartu";
          } else if (BIRTHPALCE_TALLINN_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPALCE_TALLINN_FINIS) {
              return "Tallinn";
          } else if (BIRTHPALCE_KOHTLA_JARVE_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPALCE_KOHTLA_JARVE_FINIS) {
              return "Kohtla-Järve";
          }  else if (BIRTHPALCE_TARTU_2_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPALCE_TARTU_2_FINIS) {
              return "Tartu";
          } else if (BIRTHPLACE_NARVA_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPLACE_NARVA_FINIS) {
              return "Narva";
          } else if (BIRTHPALCE_PARNU_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPLACE_PARNU_FINIS) {
              return "Pärnu";
          }  else if (BIRTHPLACE_TALLINN_2_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPLACE_TALLINN_2_FINIS) {
              return "Tallinn";
          } else if (BIRTHPLACE_PAIDE_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPLACE_PAIDE_FINIS) {
              return "Paide";
          }  else if (BIRTHPLACE_RAKVERE_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPLACE_RAKVERE_FINIS) {
              return "Rakvere";
          } else if (BIRTHPLACE_VALGA_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPLACE_VALGA_FINIS) {
              return "Valga";
          } else if (BIRTHPLACE_VILJANDI_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPLACE_VILJANDI_FINIS) {
              return "Viljandi";
          } else if (BIRTHPALCE_VORU_START <= Integer.parseInt(cityCode)
                  && Integer.parseInt(cityCode) <= BIRTHPALCE_VORU_FINIS) {
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
          String birthYear = idCodeValue.substring(1, 3);
          String firstHalf = "";
          if (firstNumber == '1' || firstNumber == '2') {
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
          return 0 < firstNumber && firstNumber < 7;
      }

      /**
       * Check if the year number is correct.
       *
       * @return boolean describing whether the year number is correct.
       */
      private boolean isYearNumberCorrect() {
          String birthYear = idCodeValue.substring(1, 3);
          return 0 <= Integer.parseInt(birthYear) && Integer.parseInt(birthYear) <= BIRTYEAR_LAST_NUMBER;
      }

      /**
       * Check if the month number is correct.
       *
       * @return boolean describing whether the month number is correct.
       */
      private boolean isMonthNumberCorrect() {
          String birthYear = idCodeValue.substring(3, 5);
          return 1 <= Integer.parseInt(birthYear) && Integer.parseInt(birthYear) <= MONTHS_MAX;
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
          int birthDay = Integer.parseInt(idCodeValue.substring(5, 7));

          if (month31.contains(birthMonth) && (0 < birthDay && birthDay <= MONTH_MAX_DAY)) {
              return true;
          } else if (month30.contains(birthMonth) && (0 < birthDay && birthDay <= MONTH_MAX_DAY_1)) {
              return true;
          } else if (Objects.equals(birthMonth, "02")) {
              if (isLeapYear(birthYear) && (0 < birthDay && birthDay <= FEBRUARY_MAX_DAY)) {
                  return true;
              } else if (!isLeapYear(birthYear) && (0 < birthDay && birthDay <= FEBRUARY_MAX_DAY_1)) {
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
          if (multiplication % CONTROL_NUMBER_CONSTANT == 10) {
              for (int i = 3; i < 10; i++) {
                  multiplication = 0;
                  multiplication += i * Integer.parseInt(String.valueOf(idCodeValue.charAt(i - 3)));
              }
              for (int i = 8; i < CONTROL_NUMBER_CONSTANT; i++) {
                  multiplication += i - 7 * Integer.parseInt(String.valueOf(idCodeValue.charAt(i)));
              }
              if (multiplication % CONTROL_NUMBER_CONSTANT == Integer.parseInt(String.valueOf(idCodeValue.charAt(10)))) {
                  return true;
              }
          } else {
              if (multiplication % CONTROL_NUMBER_CONSTANT == Integer.parseInt(String.valueOf(idCodeValue.charAt(10)))) {
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
          if (fullYear % LEAP_YEAR_DIVIDE_NUMBER == 0 || fullYear % 4 == 0) {
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
