package it.prova.triage.web.api.exception;

public class DottoreNotDeletable  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DottoreNotDeletable(String message) {
		super(message);
	}
}