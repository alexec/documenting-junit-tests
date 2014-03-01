import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

import java.io.*;

public class DocumentingTestListener extends RunListener {
    private static final PrintStream NULL_STREAM = new PrintStream(new OutputStream() {
        @Override
        public void write(int i) throws IOException {
            // nop
        }
    });
    private PrintStream out = NULL_STREAM;
    private Class<?> testClass;

    @Override
    public void testStarted(Description description) throws Exception {
        if (!description.getTestClass().equals(testClass)) {
            out.close();
            open(description);
        }
        Doc doc = description.getAnnotation(Doc.class);
        if (doc != null) {
            out.println(doc.title());
            out.println("---");
            out.println();
            out.println(doc.description());
        }
    }

    @Override
    public void testFinished(Description description) throws Exception {
        out.println();
        out.flush();
    }

    @Override
    protected void finalize() throws Throwable {
        out.close();
        super.finalize();
    }

    private void open(Description description) throws FileNotFoundException {

        testClass = description.getTestClass();
        Doc doc = testClass.getAnnotation(Doc.class);

        if (doc != null) {
            System.out.println("Documenting " + testClass);
            out = new PrintStream(new FileOutputStream(testClass.getSimpleName() + ".md"));
            out.println(doc.title());
            out.println("===");
            out.println("");
        } else {
            out = NULL_STREAM;
        }
    }
}
