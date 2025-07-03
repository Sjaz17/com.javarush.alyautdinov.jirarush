package com.javarush.jira.bugtracking.task;

import com.javarush.jira.MatcherFactory;
import com.javarush.jira.bugtracking.UserBelong;
import com.javarush.jira.bugtracking.sprint.SprintTestData;
import com.javarush.jira.bugtracking.task.to.ActivityTo;
import com.javarush.jira.bugtracking.task.to.TaskTo;
import com.javarush.jira.bugtracking.task.to.TaskToExt;
import com.javarush.jira.bugtracking.task.to.TaskToFull;
import com.javarush.jira.common.to.CodeTo;
import com.javarush.jira.login.internal.web.UserTestData;

import java.time.LocalDateTime;
import java.util.List;

import static com.javarush.jira.bugtracking.ObjectType.TASK;
import static com.javarush.jira.login.internal.web.UserTestData.ADMIN_ID;
import static com.javarush.jira.login.internal.web.UserTestData.USER_ID;

public class TaskTestData {
    public static final MatcherFactory.Matcher<Task> TASK_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Task.class, "id", "startpoint", "endpoint", "activities", "project", "sprint", "parent", "tags", "enabled");
    public static final MatcherFactory.Matcher<TaskTo> TASK_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(TaskTo.class, "id", "startpoint", "endpoint", "enabled");
    // Убираем userBelongTos из игнорируемых полей, так как его нет в TaskToFull
    public static final MatcherFactory.Matcher<TaskToFull> TASK_TO_FULL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(TaskToFull.class, "id", "updated", "activityTos", "enabled", "parent", "project", "sprint");
    public static final MatcherFactory.Matcher<Activity> ACTIVITY_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Activity.class, "id", "updated", "author", "task");
    public static final MatcherFactory.Matcher<UserBelong> USER_BELONG_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(UserBelong.class, "id", "startpoint", "endpoint");

    // --- ID константы ---
    public static final long TASK1_ID = 1L;
    public static final long TASK2_ID = 2L;
    public static final long READY_FOR_TEST_TASK_ID = 3L;
    public static final long READY_FOR_REVIEW_TASK_ID = 4L;
    public static final long TODO_TASK_ID = 5L;
    public static final long DONE_TASK_ID = 6L;
    public static final long CANCELED_TASK_ID = 7L;

    public static final long ACTIVITY1_ID = 1L;
    public static final long ACTIVITY2_ID = 2L;
    public static final long ACTIVITY3_ID = 3L;
    public static final long ACTIVITY4_ID = 4L;

    public static final long NOT_FOUND = 100L;

    // --- Статусы и типы ---
    public static final String TODO = "todo";
    public static final String IN_PROGRESS = "in_progress";
    public static final String READY_FOR_REVIEW = "ready_for_review";
    public static final String READY_FOR_TEST = "ready_for_test";
    public static final String TEST = "test";
    public static final String DONE = "done";
    public static final String CANCELED = "canceled";

    public static final String TASK_DEVELOPER = "task_developer";
    public static final String TASK_REVIEWER = "task_reviewer";
    public static final String TASK_TESTER = "task_tester";

    // --- Объекты TaskTo ---
    public static final TaskTo taskTo1 = new TaskTo(TASK1_ID, "epic-" + TASK1_ID, "Data", "epic", IN_PROGRESS, null, SprintTestData.PROJECT1_ID, SprintTestData.SPRINT1_ID);
    public static final TaskTo taskTo2 = new TaskTo(TASK2_ID, "epic-" + TASK2_ID, "Trees", "epic", IN_PROGRESS, null, SprintTestData.PROJECT1_ID, SprintTestData.SPRINT1_ID);

    // --- Объекты TaskToFull (для сравнения в get) ---
    public static final TaskToFull taskToFull1;
    public static final TaskToFull taskToFull2;

    // --- Объекты ActivityTo ---
    public static final ActivityTo activityTo1ForTask1 = new ActivityTo(
            ACTIVITY1_ID,
            TASK1_ID,
            UserTestData.USER_ID,
            null,
            null,
            IN_PROGRESS,
            "normal", // Соответствует логу для taskToFull1.priorityCode
            "epic",
            "Data",
            null,    // Соответствует логу для taskToFull1.description
            4,       // Соответствует логу для taskToFull1.estimate
            null
    );
    public static final ActivityTo activityTo2ForTask1 = new ActivityTo(
            ACTIVITY2_ID,
            TASK1_ID,
            UserTestData.ADMIN_ID,
            null,
            "Admin comment for Data",
            null,
            "normal",
            null,
            "Data",
            null,
            null,
            null
    );
    public static final ActivityTo activityTo3ForTask1 = new ActivityTo(
            ACTIVITY3_ID,
            TASK1_ID,
            UserTestData.USER_ID,
            null,
            "User's second comment",
            null,
            null,
            null,
            "Data",
            null,
            4,
            null
    );
    public static final List<ActivityTo> activityTosForTask1 = List.of(activityTo3ForTask1, activityTo2ForTask1, activityTo1ForTask1);


    // --- Объекты UserBelong (для сравнения в getTaskAssignmentsBySprint) ---
    public static final UserBelong userBelongTask1User1Developer = new UserBelong(TASK1_ID, TASK, UserTestData.USER_ID, TASK_DEVELOPER);
    public static final UserBelong userBelongTask1User1Tester = new UserBelong(TASK1_ID, TASK, UserTestData.USER_ID, TASK_TESTER);
    public static final UserBelong userBelongTask2User1Developer = new UserBelong(TASK2_ID, TASK, UserTestData.USER_ID, TASK_DEVELOPER);
    public static final UserBelong userBelongTask2User1Tester = new UserBelong(TASK2_ID, TASK, UserTestData.USER_ID, TASK_TESTER);
    public static final UserBelong userBelongTask1AdminDeveloper = new UserBelong(TASK1_ID, TASK, UserTestData.ADMIN_ID, TASK_DEVELOPER);
    public static final UserBelong userBelongTask1AdminReviewer = new UserBelong(TASK1_ID, TASK, UserTestData.ADMIN_ID, TASK_REVIEWER);


    static {
        CodeTo project1CodeTo = new CodeTo(SprintTestData.PROJECT1_ID, "PR1");
        CodeTo sprint1CodeTo = new CodeTo(SprintTestData.SPRINT1_ID, "SP-1.001");

        // Инициализация taskToFull1 в соответствии с логом ошибки
        // Убедитесь, что конструктор TaskToFull соответствует этому набору полей
        taskToFull1 = new TaskToFull(
                Long.valueOf(TASK1_ID),         // ИЗМЕНЕНО: long -> Long
                "epic-1",                       // code
                "Data",                         // title
                null,                           // description (String)
                "epic",                         // typeCode
                IN_PROGRESS,                    // statusCode
                "normal",                       // priorityCode (String)
                (LocalDateTime) null,           // updated (LocalDateTime) - каст для ясности, но null и так подойдет
                Integer.valueOf(4),             // ИЗМЕНЕНО: int -> Integer
                (CodeTo) null,                  // parent (CodeTo) - каст для ясности
                project1CodeTo,                 // project (CodeTo)
                sprint1CodeTo,                  // sprint (CodeTo)
                // true,                        // <<--- УБРАН ЛИШНИЙ АРГУМЕНТ 'enabled'
                activityTosForTask1             // activityTos (List<ActivityTo>)
        );

        TaskToExt updatedTask2Data = getUpdatedTaskTo();

        ActivityTo activityForUpdatedTask2 = new ActivityTo(
                null,
                TASK2_ID,
                UserTestData.USER_ID,
                null,
                updatedTask2Data.getDescription(),
                updatedTask2Data.getStatusCode(),
                updatedTask2Data.getPriorityCode(),
                updatedTask2Data.getTypeCode(),
                updatedTask2Data.getTitle(),
                updatedTask2Data.getDescription(),
                updatedTask2Data.getEstimate(),
                null
        );


        taskToFull2 = new TaskToFull(
                Long.valueOf(TASK2_ID),        // ИЗМЕНЕНО: long -> Long
                updatedTask2Data.getCode(),
                updatedTask2Data.getTitle(),
                updatedTask2Data.getDescription(),
                updatedTask2Data.getTypeCode(),
                updatedTask2Data.getStatusCode(),
                updatedTask2Data.getPriorityCode(),
                (LocalDateTime) null,          // updated (LocalDateTime)
                // estimate может быть null в updatedTask2Data, поэтому нужна проверка
                updatedTask2Data.getEstimate() != null ? Integer.valueOf(updatedTask2Data.getEstimate()) : null, // ИЗМЕНЕНО: int -> Integer
                (CodeTo) null,                 // parent (CodeTo)
                project1CodeTo,                // project (CodeTo)
                sprint1CodeTo,                 // sprint (CodeTo)
                // true,                       // <<--- УБРАН ЛИШНИЙ АРГУМЕНТ 'enabled'
                List.of(activityForUpdatedTask2) // activityTos (List<ActivityTo>)
        );

    }

    public static TaskToExt getNewTaskTo() {
        return new TaskToExt(null,
                "NEWTASKCODE-" + System.currentTimeMillis(),
                "New Task Title From TestData",
                "New task description from TestData",
                "task",
                TODO,
                "normal",
                null,
                2,
                null,
                SprintTestData.PROJECT1_ID,
                SprintTestData.SPRINT1_ID);
    }

    public static ActivityTo getNewActivityTo(long targetTaskId) {
        return new ActivityTo(null, targetTaskId, UserTestData.USER_ID, null, "New activity comment",
                READY_FOR_REVIEW, null, "epic", "New Activity Title", "New Activity Desc", 4, null);
    }

    public static TaskToExt getUpdatedTaskTo() {
        return new TaskToExt(TASK2_ID,
                taskTo2.getCode(),
                taskTo2.getTitle() + " UPDATED",
                "Description for Trees task UPDATED",
                taskTo2.getTypeCode(),
                READY_FOR_REVIEW,
                "high",
                null,
                5,
                taskTo2.getParentId(),
                taskTo2.getProjectId(),
                taskTo2.getSprintId()
        );
    }

    public static TaskToExt getOriginalTask2Ext() {
        return new TaskToExt(
                TASK2_ID,
                taskTo2.getCode(),
                taskTo2.getTitle(),
                null,
                taskTo2.getTypeCode(),
                taskTo2.getStatusCode(),
                null,
                null,
                null,
                taskTo2.getParentId(),
                taskTo2.getProjectId(),
                taskTo2.getSprintId()
        );
    }

    public static ActivityTo getUpdatedActivityTo() {
        return new ActivityTo(ACTIVITY1_ID, TASK1_ID, UserTestData.USER_ID, null, "Updated comment for activity 1",
                IN_PROGRESS, "high", "epic", "Data - Updated Title", "Updated description", 5, null);
    }
}
