# Test Plan for Institution/Branch Request Management

## 1. Add Institution (Headquarter)

### 1.1 Add New Headquarter - Success
- **Precondition:** No institution with the same name, country, and city exists as a headquarter.
- **Steps:**
  1. Open `request.jsp`.
  2. Select "No" for "Is this institution a branch?" (mark as headquarter).
  3. Fill all required fields with valid data.
  4. Submit the form.
- **Expected Result:** Institution is saved; success message is shown.

### 1.2 Add Duplicate Headquarter - Error
- **Precondition:** Institution with the same name already exists as a headquarter.
- **Steps:**
  1. Open `request.jsp`.
  2. Select "No" for branch.
  3. Enter the same name as an existing headquarter.
  4. Submit the form.
- **Expected Result:** Error message indicating duplicate headquarter.

---

## 2. Add Branch

### 2.1 Add New Branch - Success
- **Precondition:** Headquarter institution exists.
- **Steps:**
  1. Open `request.jsp`.
  2. Select "Yes" for branch.
  3. Select an existing headquarter.
  4. Fill all required fields with valid data (different country/city from existing branches).
  5. Submit the form.
- **Expected Result:** Branch is saved; success message is shown.

### 2.2 Add Duplicate Branch (Same Name, Country, City) - Error
- **Precondition:** Branch with same name, country, and city exists.
- **Steps:**
  1. Open `request.jsp`.
  2. Select "Yes" for branch.
  3. Select the same headquarter.
  4. Enter same name, country, and city as existing branch.
  5. Submit the form.
- **Expected Result:** Error message indicating duplicate branch.

---

## 3. Field Validation

### 3.1 Acronym Length
- **Steps:** Enter acronym longer than 10 characters.
- **Expected Result:** Validation error; cannot submit.

### 3.2 Name Length
- **Steps:** Enter name longer than 10 words.
- **Expected Result:** Validation error; cannot submit.

### 3.3 Required Fields
- **Steps:** Leave required fields (name, type, country, city) empty.
- **Expected Result:** Validation error; cannot submit.

### 3.4 Website URL Format
- **Steps:** Enter website not starting with `http` or `https`.
- **Expected Result:** Validation error; cannot submit.

---

## 4. List Institutions

### 4.1 List All Institutions
- **Steps:** Open `listrequest.jsp`.
- **Expected Result:** All institutions and branches are displayed in the table.

### 4.2 List Institutions for Branch Selection
- **Steps:** Open `request.jsp` as branch; check headquarter dropdown.
- **Expected Result:** Only headquarters are listed for selection.

---

## Notes

- All data is persisted in a JSON file (`file.json`).
- Duplicate checks are based on name (for headquarters) and name + country + city (for branches).
- All validation rules are enforced both client-side (JavaScript) and server-side (Java).