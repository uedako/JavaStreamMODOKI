package seq;

public class Collects {

	public static CollectFunc<String> join(final String sep) {
		return new CollectFunc<String>() {
			@Override
			public <TSeq> String invoke(Seq<TSeq> seq) {
				boolean isFirst = true;
				StringBuilder sb = new StringBuilder();
				for (TSeq value : seq) {
					if (isFirst) {
						isFirst = false;
					} else {
						sb.append(sep);
					}
					sb.append(value == null ? "" : value.toString());
				}
				return sb.toString();
			}
		};
	}
}
