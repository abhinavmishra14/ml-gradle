package com.marklogic.gradle.task

import org.gradle.api.tasks.TaskAction

/**
 * Parent class for tasks that benefit from some confirmation in the way of requiring a property named "confirm" to be
 * set to "true". Tasks that can benefit from this simply need to extend this and implement the
 * "executeIfConfirmed" method.
 */
abstract class AbstractConfirmableTask extends MarkLogicTask {

	@TaskAction
	void executeTask() {
		boolean executed = false
		if (project.hasProperty("confirm")) {
			if ("true".equals(project.property("confirm"))) {
				executed = true
				executeIfConfirmed()
			}
		}

		if (!executed) {
			println "To execute this task, set the 'confirm' property to 'true'; e.g. '-Pconfirm=true'"
		}
	}

	abstract void executeIfConfirmed()
}
