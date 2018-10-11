package br.ufc.russas.n2s.chronos.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static final SessionFactory SESSION_FACTORY;
	private static final ThreadLocal SESSION_THREAD = new ThreadLocal();
	private static final ThreadLocal TRANSACTION_THREAD = new ThreadLocal();
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://192.169.1.2:5432/chronos");
			configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
			configuration.setProperty("hibernate.connection.username", "n2s");
			configuration.setProperty("hibernate.connection.password", "N2S@UFC");
			// configuration.setProperty("hibernate.order_updates", "true");
			// configuration.setProperty("hibernate.cache.provider_class",
			// "org.hibernate.cache.NoCacheProvider");
			// configuration.setProperty("hibernate.show_sql", "false");
			configuration.setProperty("hibernate.format_sql", "true");
			// configuration.setProperty("hibernate.current_session_context_class",
			// "thread");
			// Scanner.scan(Thread.currentThread().getContextClassLoader(),
			// Collections.EMPTY_SET, Collections.EMPTY_SET, configuration);//é um metodo
			// que efetua o addanotationclass automatico ou você pode fazer varios
			// addannotationsclass e substituir esse metodo.
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			SESSION_FACTORY = configuration.configure("/hibernate.cfg.xml").buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() {
		if (SESSION_THREAD.get() == null) {
			Session session = SESSION_FACTORY.openSession();
			SESSION_THREAD.set(session);
		}
		return (Session) SESSION_THREAD.get();
	}

	public static void closeSession() {
		Session session = (Session) SESSION_THREAD.get();
		if (session != null && session.isOpen()) {
			SESSION_THREAD.set(null);
			session.close();
		}
	}

	public static void beginTransaction() {
		Transaction transaction = getSession().beginTransaction();
		TRANSACTION_THREAD.set(transaction);
	}

	public static void commitTransaction() {
		Transaction transaction = (Transaction) TRANSACTION_THREAD.get();
		if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
			transaction.commit();
			TRANSACTION_THREAD.set(null);
		}
	}

	public static void rollbackTransaction() {
		Transaction transaction = (Transaction) TRANSACTION_THREAD.get();
		if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
			transaction.rollback();
			TRANSACTION_THREAD.set(null);
		}
	}
}