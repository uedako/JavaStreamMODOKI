package seq;

public interface CollectFunc<T> {
	<TSeq> T invoke(Seq<TSeq> seq);
}
