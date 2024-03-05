package dao;

import java.util.List;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.neo4j.driver.types.Node;

public class NorthwindDAO {
	private Driver driver;
	private SessionConfig sessionConfig;
	
	public NorthwindDAO(Driver driver, String dbName) {
		this.driver = driver;
		sessionConfig = SessionConfig
				.builder()
//				.withDefaultAccessMode(AccessMode.WRITE)
				.withDatabase(dbName)
				.build();
	}
	
	/**
	 * Add new course to the database
	 * @param course Course to be added
     * @return void
     * String query = "CREATE (a:Course {courseID: $id, name: $name, hours: $hours})";
	 */
	
//	public void addCourse(Course course) {
//		String query = "CREATE (a:Course {courseID: $id, name: $name, hours: $hours})";
//		Map<String, Object> pars = Map.of(
//				"id", course.getCourseId(),
//				"name", course.getName(),
//				"hours", course.getHours()
//				);
//
//		try(Session session = driver.session(sessionConfig)){
//
//			session.executeWrite(tx ->{
//				return tx.run(query, pars).consume();
//			});
//		}
//
////		Session session = null;
////		try{
////			session = driver.session(sessionConfig)
////
////		}finally {
////			if(session != null) {
////                session.close();
////            }
////		}
//	}
//
//	/**
//	 * Add new course to the database
//	 * @param course Course to be added
//     * @return course's id
//	 */
//	public String addCourse2(Course course) {
//		String query = "CREATE (a:Course {courseID: $id, name: $name, hours: $hours}) "
//				+ "RETURN a.courseID";
//		Map<String, Object> pars = Map.of(
//				"id", course.getCourseId(),
//                "name", course.getName(),
//                "hours", course.getHours()
//                );
//
//		try(Session session = driver.session(sessionConfig)){
//			return session.executeWrite(tx -> {
//				Result result = tx.run(query, pars);
//				return result.single().get("a.courseID").asString();
//			});
//		}
//	}
//
//	/**
//	 * Find course by id
//	 * @param courseID Course's id
//     * @return Course if found, null otherwise
//     *
//	 */
//	public Course findCourseByID(String courseID) {
//		String query = "MATCH (c:Course) "
//				+ "WHERE c.courseID = $id "
//				+ "RETURN c";
//
//		Map<String, Object> pars = Map.of("id", courseID);
//
//		try(Session session = driver.session(sessionConfig)){
//			return session.executeRead(tx -> {
//				Result result = tx.run(query, pars);
//
//				if(!result.hasNext())
//					return null;
//
//				Record record = result.stream().findFirst().get();
//				Node node = record.get("c").asNode();
//
//				String id = node.get("courseID").asString();
//				String name = node.get("name").asString();
//				int hours = node.get("hours").asInt();
//
//				return new Course(id, name, hours);
//			});
//		}
//	}
//
//	/**
//	 * Update course's information
//	 * @param course Course to be updated
//     * @return void
//     * String query = "MERGE (c:Course {courseID: $id}) "
//                + "SET c.name = $name, c.hours = $hours";
//     *
//	 */
//
//	/**
//	 * Delete course by id
//	 */
//
//	/**
//	 * Add course to department
//	 * @param courseID Course's id
//	 * @param deptID Department's id
//     * @return void
//     * String query = "MATCH (c:Course {courseID: $courseID}) "
//                + "MATCH (d:Department {deptID: $deptID}) "
//                + "MERGE (c)-[:BELONGS_TO]->(d)";
//     *
//	 */
//
//	/**
//	 * Get list of courses, limit to n
//	 * @param n number of courses to get
//     * @return void
//     * String query = "MATCH (c:Course) RETURN c limit $n";
//	 */
//
//	public List<Course> listCourses(int n) {
//		String query = "MATCH (c:Course) RETURN c limit $n";
//		Map<String, Object> pars = Map.of("n", n);
//
//		try (Session session = driver.session(sessionConfig)) {
//			return session.executeRead(tx -> {
//				Result result = tx.run(query, pars);
//				return result
//						.stream() // Stream<Record>
//						.map(record -> {
//							Node node = record.get("c").asNode();
//                            String id = node.get("courseID").asString();
//                            String name = node.get("name").asString();
//                            int hours = node.get("hours").asInt();
//                            return new Course(id, name, hours);
//						}).toList();
//			});
//		}
//	}
	
	
	public void close() {
		if (driver != null) {
			driver.close();
		}
	}
}


