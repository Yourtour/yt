package com.yt.test.hbase;

import java.util.List;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;

import com.yt.test.hbase.bean.TestBean;

public class TestSpringHbaseIntegrated {

	public static void main(String[] args) throws Exception {
		System.out.println(System.currentTimeMillis());
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "hbaseApplicationContext.xml" });
		HbaseTemplate template = (HbaseTemplate) context.getBean(
				"hbaseTemplate", HbaseTemplate.class);
		final TestBean bean = new TestBean();
		bean.setTableName("tableName");
		bean.setRowKey("rowKey");
		bean.setFamily("family");
		bean.setQualifier("qualifier");
		bean.setValue("value 1234567890");
		System.out
				.println(String
						.format("Bean: \n row = %s, family = %s, qualifier = %s, value = %s, time = %d",
								bean.getRowKey(), bean.getFamily(),
								bean.getQualifier(), bean.getValue(),
								bean.getTimestamp()));
		System.out.println(System.currentTimeMillis());

		// create table
		Connection conn = ConnectionFactory.createConnection(template
				.getConfiguration());
		System.out.println(System.currentTimeMillis());
		Admin admin = conn.getAdmin();
		System.out.println(System.currentTimeMillis());
		if (!admin.tableExists(TableName.valueOf(bean.getTableName()))) {
			HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(bean
					.getTableName()));
			HColumnDescriptor col1 = new HColumnDescriptor(bean.getFamily());
			desc.addFamily(col1);
			admin.createTable(desc);
			System.out.println("create hbase table success.");
		} else {
			System.out.println("The table existed.");
		}

		System.out.println(System.currentTimeMillis());
		template.execute(bean.getTableName(), new TableCallback<TestBean>() {

			@Override
			public TestBean doInTable(HTableInterface table) throws Throwable {
				Put put = new Put(Bytes.toBytes(bean.getRowKey()));
				put.add(Bytes.toBytes(bean.getFamily()),
						Bytes.toBytes(bean.getQualifier()),
						System.currentTimeMillis(),
						Bytes.toBytes((String)bean.getValue()));
				table.put(put);
				return bean;
			}
		});
		System.out.println("put a row data success.");

		System.out.println(System.currentTimeMillis());
		Scan scan = new Scan();
		scan.addColumn(Bytes.toBytes("family"), Bytes.toBytes("qualifier"));
		scan.setMaxVersions();
		// scan.setRaw(true);
		scan.setMaxResultSize(10);
		List<TestBean> result = template.find(bean.getTableName(), scan,
				new RowMapper<TestBean>() {

					@Override
					public TestBean mapRow(Result result, int rowNum)
							throws Exception {
						Cell cell = result.getColumnLatestCell(
								Bytes.toBytes(bean.getFamily()),
								Bytes.toBytes(bean.getQualifier()));
						bean.setRowKey(Bytes.toString(cell.getRowArray(),
								cell.getRowOffset(), cell.getRowLength()));
						bean.setFamily(Bytes.toString(cell.getFamilyArray(),
								cell.getFamilyOffset(), cell.getFamilyLength()));
						bean.setQualifier(Bytes.toString(
								cell.getQualifierArray(),
								cell.getQualifierOffset(),
								cell.getQualifierLength()));
						bean.setValue(Bytes.toString(cell.getValueArray(),
								cell.getValueOffset(), cell.getValueLength()));
						bean.setTimestamp(cell.getTimestamp());
						return bean;
					}

				});
		for (TestBean b : result) {
			System.out
					.println(String
							.format("Bean: \n row = %s, family = %s, qualifier = %s, value = %s, time = %d",
									b.getRowKey(), b.getFamily(),
									b.getQualifier(), b.getValue(),
									b.getTimestamp()));
		}

		System.out.println(System.currentTimeMillis());
		Table table = conn.getTable(TableName.valueOf("tableName"));
		Get get = new Get(Bytes.toBytes("rowKey"));
		get.setMaxVersions(10);
		get.addColumn(Bytes.toBytes("family"), Bytes.toBytes("qualifier"));
		Result rs = table.get(get);
		for (final Cell cell : rs.listCells()) {
			System.out
					.println(String
							.format("Bean: \n row = %s, family = %s, qualifier = %s, value = %s, time = %d",
									Bytes.toString(cell.getRowArray(),
											cell.getRowOffset(),
											cell.getRowLength()), (Bytes
											.toString(cell.getFamilyArray(),
													cell.getFamilyOffset(),
													cell.getFamilyLength())),
									Bytes.toString(cell.getQualifierArray(),
											cell.getQualifierOffset(),
											cell.getQualifierLength()), Bytes
											.toString(cell.getValueArray(),
													cell.getValueOffset(),
													cell.getValueLength()),
									cell.getTimestamp()));
		}
		System.out.println(System.currentTimeMillis());
	}
}
