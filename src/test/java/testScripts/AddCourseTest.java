package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import genericUtilities.IConstantPath;

public class AddCourseTest extends BaseClass
{
	SoftAssert soft = new SoftAssert();
	
    @Test
    public void addCourseTest()
    {
    	home.clickCoursesTab();
    	home.clickCourseListLink();
    	soft.assertTrue(courseList.getPageHeader().contains("Course List"));
    	courseList.clickNewButton();
    	soft.assertEquals(addCourse.getPageHeader(), "Add New Course");
    	
    	Map<String , String > map = excel.readFromExcel("Add Course");
    	addCourse.setName("Name");
    	addCourse.selectCategory(web, map.get("Category"));
    	addCourse.setPrice(map.get("Price"));
    	addCourse.uploadPhoto(map.get("Photo"));
    	addCourse.setDescription(map.get("Description"), web);
    	addCourse.clickSave();
    	
    	soft.assertEquals(courseList.getSuccessMessage(), "Success!");
       // System.out.println(courseList.getSuccessMessage());
        courseList.deleteCourse(web, map.get("Name"));
       // System.out.println(courseList.getSuccessMessage());
        soft.assertEquals(courseList.getSuccessMessage(), "Success!");
    
        if(courseList.getSuccessMessage().equals("Success!"))
 		   excel.updateTestStatus("Add Course", "Pass", IConstantPath.EXCEL_PATH);
        else
 		   excel.updateTestStatus("Add Course", "Fail", IConstantPath.EXCEL_PATH);

        soft.assertAll();
        
    }
	
}
