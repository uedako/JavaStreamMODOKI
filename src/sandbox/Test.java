package sandbox;

import java.util.ArrayList;
import java.util.List;

import seq.Action;
import seq.Converter;
import seq.Else;
import seq.Filter;
import seq.Opt;
import seq.Seq;

public class Test {

	public static void main(String[] args) {

		List<String> data = new ArrayList<>();
		data.add("hage1");
		for (int i = 0; i < 100; i++) {
			data.add("hoge" + i);
		}

		Seq<String> seq = Seq.of(data);
		// debug("IterableSeq", seq);

		long start = System.currentTimeMillis();

		Seq<String> map = seq.filter(new Filter<String>() {
			@Override
			public boolean doFilter(String value) {
				return 0 < value.indexOf("1");
			}
		}).peek(new Action<String>() {
			@Override
			public void invoke(String value) {
				System.out.println("peek:" + value);
			}
		}).map(new Converter<String, String>() {
			@Override
			public String convert(String value) {
				return value + "HHHH";
			}
		});

		debug("MapSeq", map);

		System.out.println("--- opt test ---");

		Opt<String> first = map.first();

		first.ifPresent(new Action<String>() {
			@Override
			public void invoke(String value) {
				System.out.println("first:" + value);
			}
		});

		Opt<Integer> mapOpt = first.map(new Converter<String, Integer>() {
			@Override
			public Integer convert(String value) {
				return value.length();
			}
		});
		mapOpt.ifPresent(new Action<Integer>() {
			@Override
			public void invoke(Integer value) {
				System.out.println("map:" + value);
			}
		});

		Opt<Integer> filterOpt = mapOpt.filter(new Filter<Integer>() {
			@Override
			public boolean doFilter(Integer value) {
				return value == 7;
			}
		});
		filterOpt.ifPresent(new Action<Integer>() {
			@Override
			public void invoke(Integer value) {
				System.out.println("filter:" + value);
			}
		});

		Integer orElse = filterOpt.orElse(new Else<Integer>() {
			@Override
			public Integer invoke() {
				return -1;
			}
		});

		System.out.println("orElse:" + orElse);

		long end = System.currentTimeMillis();
		System.out.println((end - start) + "ms");
	}

	private static void debug(String title, Seq<String> seq) {
		System.out.println("---" + title + "---");
		for (String value : seq) {
			System.out.println(value);
		}
	}

}
