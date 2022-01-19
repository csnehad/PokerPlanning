/*
 * package com.pokerrestapi.controller;
 * 
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import javax.persistence.EntityNotFoundException;
 * 
 * import org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.ControllerAdvice; import
 * org.springframework.web.bind.annotation.ExceptionHandler; import
 * org.springframework.web.context.request.WebRequest;
 * 
 * import com.pokerrestapi.exception.AddUpdateMemberException; import
 * com.pokerrestapi.exception.ErrorResponse; import
 * com.pokerrestapi.exception.FetchMemberException; import
 * com.pokerrestapi.exception.FetchUserStoryException; import
 * com.pokerrestapi.exception.MemberNotFoundException; import
 * com.pokerrestapi.exception.SessionNotFoundException; import
 * com.pokerrestapi.exception.UserStoryDeleteNotAvailableException; import
 * com.pokerrestapi.exception.UserStoryNotFoundException; import
 * com.pokerrestapi.exception.VoteNotAcceptedException; import
 * com.pokerrestapi.exception.VotePointOutOfRangeException;
 * 
 * @SuppressWarnings({"unchecked","rawtypes"})
 * 
 * @ControllerAdvice public class ExceptionController extends RuntimeException{
 *//**
	* 
	*//*
		 * private static final long serialVersionUID = -8459938318611124019L;
		 * 
		 * @ExceptionHandler(Exception.class) public final ResponseEntity<Object>
		 * handleAllExceptions(Exception ex, WebRequest request) { List<String> details
		 * = new ArrayList<>(); details.add(ex.getLocalizedMessage()); ErrorResponse
		 * error = new ErrorResponse("Server Error", details); return new
		 * ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR); }
		 * 
		 * @ExceptionHandler(EntityNotFoundException.class) public final
		 * ResponseEntity<Object> handleEntityNotFoundExceptions(Exception ex,
		 * WebRequest request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("Entity not Present", details); return new
		 * ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR); }
		 * 
		 * @ExceptionHandler(SessionNotFoundException.class) public final
		 * ResponseEntity<Object>
		 * handleSessionNotFoundException(SessionNotFoundException ex, WebRequest
		 * request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("Session Not Found", details); return new ResponseEntity(error,
		 * HttpStatus.NOT_FOUND); }
		 * 
		 * @ExceptionHandler(UserStoryNotFoundException.class) public final
		 * ResponseEntity<Object>
		 * handleUserStoryNotFoundException(SessionNotFoundException ex, WebRequest
		 * request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("UserStory Not Found", details); return new
		 * ResponseEntity(error, HttpStatus.NOT_FOUND); }
		 * 
		 * @ExceptionHandler(MemberNotFoundException.class) public final
		 * ResponseEntity<Object> handleMemberNotFoundException(SessionNotFoundException
		 * ex, WebRequest request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("Member Not Found", details); return new ResponseEntity(error,
		 * HttpStatus.NOT_FOUND); }
		 * 
		 * @ExceptionHandler(VoteNotAcceptedException.class) public final
		 * ResponseEntity<Object>
		 * handleVoteNotAcceptedException(SessionNotFoundException ex, WebRequest
		 * request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("Vote Not Accepted", details); return new ResponseEntity(error,
		 * HttpStatus.NOT_ACCEPTABLE); }
		 * 
		 * @ExceptionHandler(UserStoryDeleteNotAvailableException.class) public final
		 * ResponseEntity<Object>
		 * handleUserStoryDeleteNotAvailableException(SessionNotFoundException ex,
		 * WebRequest request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("UserStory Delete Not Available", details); return new
		 * ResponseEntity(error, HttpStatus.NOT_ACCEPTABLE); }
		 * 
		 * @ExceptionHandler(FetchMemberException.class) public final
		 * ResponseEntity<Object> handleFetchMemberException(SessionNotFoundException
		 * ex, WebRequest request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("Unable to fetch Member List", details); return new
		 * ResponseEntity(error, HttpStatus.NOT_FOUND); }
		 * 
		 * @ExceptionHandler(FetchUserStoryException.class) public final
		 * ResponseEntity<Object> handleFetchUserStoryException(SessionNotFoundException
		 * ex, WebRequest request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("Unable to fetch UserStory List", details); return new
		 * ResponseEntity(error, HttpStatus.NOT_FOUND); }
		 * 
		 * @ExceptionHandler(AddUpdateMemberException.class) public final
		 * ResponseEntity<Object>
		 * handleAddUpdateMemberException(SessionNotFoundException ex, WebRequest
		 * request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("Unable to Add or Update Member to this session ", details);
		 * return new ResponseEntity(error, HttpStatus.CONFLICT); }
		 * 
		 * @ExceptionHandler(VotePointOutOfRangeException.class) public final
		 * ResponseEntity<Object>
		 * handleVotePointOutOfRangeException(SessionNotFoundException ex, WebRequest
		 * request) { List<String> details = new ArrayList<>();
		 * details.add(ex.getLocalizedMessage()); ErrorResponse error = new
		 * ErrorResponse("Entered Vote Ponit is not in range", details); return new
		 * ResponseEntity(error, HttpStatus.NOT_ACCEPTABLE); }
		 * 
		 * 
		 * }
		 */