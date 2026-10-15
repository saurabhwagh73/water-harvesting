import { useEffect, useState } from 'react';

function useApi(path, fallback) {
  const [data, setData] = useState(fallback);
  const [error, setError] = useState(false);
  useEffect(() => {
    fetch(`/api/${path}`)
      .then((r) => (r.ok ? r.json() : Promise.reject()))
      .then(setData)
      .catch(() => setError(true));
  }, [path]);
  return [data, error];
}

const Section = ({ id, title, subtitle, children, alt }) => (
  <section id={id} className={alt ? 'section alt' : 'section'}>
    <div className="container">
      <h2>{title}</h2>
      {subtitle && <p className="subtitle">{subtitle}</p>}
      {children}
    </div>
  </section>
);

function Navbar() {
  const links = [['about', 'About'], ['how', 'How It Works'], ['benefits', 'Benefits'],
    ['calculator', 'Calculator'], ['world', 'Worldwide'], ['history', 'History'], ['faq', 'FAQ']];
  return (
    <nav className="nav">
      <a href="#top" className="brand">💧 RainHarvest</a>
      <div className="nav-links">
        {links.map(([id, label]) => <a key={id} href={`#${id}`}>{label}</a>)}
      </div>
    </nav>
  );
}

function Hero({ overview }) {
  return (
    <header id="top" className="hero">
      <div className="container hero-inner">
        <div>
          <h1>{overview?.title ?? 'Rainwater Harvesting'}</h1>
          <p className="tagline">{overview?.tagline}</p>
          <a className="btn" href="#calculator">Calculate my savings</a>
          <a className="btn ghost" href="#how">See how it works</a>
        </div>
        <img src="/images/rwh-system-3d.png" alt="Rainwater harvesting system from roof to well" />
      </div>
    </header>
  );
}

function About({ overview }) {
  if (!overview) return null;
  return (
    <Section id="about" title="What is Rainwater Harvesting?" subtitle={overview.definition}>
      <div className="stats">
        {overview.stats.map((s) => (
          <div className="stat" key={s.label}><strong>{s.value}</strong><span>{s.label}</span></div>
        ))}
      </div>
      <h3>Common uses</h3>
      <div className="chips">{overview.uses.map((u) => <span key={u} className="chip">{u}</span>)}</div>
      <figure className="figure">
        <img src="/images/water-cycle.png" alt="The water cycle" />
        <figcaption>The water cycle: rainwater harvesting intercepts precipitation before it is lost as runoff.</figcaption>
      </figure>
    </Section>
  );
}

function HowItWorks() {
  const [components] = useApi('components', []);
  return (
    <Section id="how" alt title="How a System Works"
      subtitle="Rain falls on the roof, flows through gutters and a filter into a tank, is pumped to where it is needed, and any overflow recharges the groundwater.">
      <img className="wide" src="/images/rwh-system-diagram.png" alt="Rainwater harvesting diagram" />
      <div className="grid">
        {components.map((c) => (
          <div className="card" key={c.step}>
            <span className="step">{c.step}</span>
            <h3>{c.name}</h3>
            <p>{c.description}</p>
          </div>
        ))}
      </div>
    </Section>
  );
}

function Benefits() {
  const [benefits] = useApi('benefits', []);
  return (
    <Section id="benefits" title="Why Harvest Rainwater?">
      <div className="grid">
        {benefits.map((b) => (
          <div className="card" key={b.title}>
            <div className="icon">{b.icon}</div>
            <h3>{b.title}</h3>
            <p>{b.description}</p>
          </div>
        ))}
      </div>
    </Section>
  );
}

function Calculator() {
  const [form, setForm] = useState({
    roofAreaM2: 100, annualRainfallMm: 800, runoffCoefficient: 0.8, dailyDemandLitres: 200, dryDays: 60,
  });
  const [result, setResult] = useState(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const change = (e) => setForm({ ...form, [e.target.name]: Number(e.target.value) });

  const submit = async () => {
    setLoading(true); setError(''); setResult(null);
    try {
      const res = await fetch('/api/calculate', {
        method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(form),
      });
      const body = await res.json();
      if (!res.ok) throw new Error(body.error || 'Invalid input');
      setResult(body);
    } catch (e) {
      setError(e.message || 'Could not reach the server. Is the backend running?');
    } finally { setLoading(false); }
  };

  const fields = [
    ['roofAreaM2', 'Roof area (m²)'], ['annualRainfallMm', 'Annual rainfall (mm)'],
    ['runoffCoefficient', 'Runoff coefficient (0.1 - 1.0)'], ['dailyDemandLitres', 'Daily water demand (litres)'],
    ['dryDays', 'Longest dry spell (days)'],
  ];

  return (
    <Section id="calculator" alt title="Rainwater Yield Calculator"
      subtitle="Yield (litres) = roof area (m²) × rainfall (mm) × runoff coefficient. Every 1 mm of rain on 1 m² gives 1 litre.">
      <div className="calc">
        <div className="calc-form">
          {fields.map(([name, label]) => (
            <label key={name}>{label}
              <input type="number" step="any" name={name} value={form[name]} onChange={change} />
            </label>
          ))}
          <button className="btn" onClick={submit} disabled={loading}>{loading ? 'Calculating…' : 'Calculate'}</button>
          {error && <p className="error">{error}</p>}
        </div>
        <div className="calc-result">
          {result ? (
            <>
              <div className="big">{result.annualYieldLitres.toLocaleString()} L<small>collectable per year</small></div>
              <ul>
                <li>Annual demand: <b>{result.annualDemandLitres.toLocaleString()} L</b></li>
                <li>Demand covered: <b>{result.coveragePercent}%</b></li>
                <li>Suggested tank: <b>{result.suggestedTankLitres.toLocaleString()} L</b></li>
              </ul>
              <div className="bar"><div style={{ width: `${result.coveragePercent}%` }} /></div>
              <p>{result.message}</p>
            </>
          ) : <p className="muted">Enter your details and press Calculate.</p>}
        </div>
      </div>
    </Section>
  );
}

function World() {
  const [regions] = useApi('regions', []);
  return (
    <Section id="world" title="Rainwater Harvesting Around the World">
      <div className="grid">
        {regions.map((r) => (
          <div className="card" key={r.name}>
            <div className="icon">{r.flag}</div>
            <h3>{r.name}</h3>
            <p>{r.highlight}</p>
          </div>
        ))}
      </div>
    </Section>
  );
}

function History() {
  const [events] = useApi('history', []);
  return (
    <Section id="history" alt title="A Very Old Idea" subtitle="People have been capturing rain for thousands of years.">
      <div className="timeline">
        {events.map((e) => (
          <div className="t-item" key={e.period + e.place}>
            <span className="t-period">{e.period}</span>
            <h3>{e.place}</h3>
            <p>{e.description}</p>
          </div>
        ))}
      </div>
    </Section>
  );
}

function Faq() {
  const [faqs] = useApi('faqs', []);
  const [open, setOpen] = useState(0);
  return (
    <Section id="faq" title="Frequently Asked Questions">
      <div className="faq">
        {faqs.map((f, i) => (
          <div className={`faq-item ${open === i ? 'open' : ''}`} key={f.question}>
            <button onClick={() => setOpen(open === i ? -1 : i)}>{f.question}<span>{open === i ? '−' : '+'}</span></button>
            {open === i && <p>{f.answer}</p>}
          </div>
        ))}
      </div>
    </Section>
  );
}

export default function App() {
  const [overview, error] = useApi('overview', null);
  return (
    <>
      <Navbar />
      {error && <div className="banner">Backend not reachable. Start Spring Boot on port 8080.</div>}
      <Hero overview={overview} />
      <About overview={overview} />
      <HowItWorks />
      <Benefits />
      <Calculator />
      <World />
      <History />
      <Faq />
      <footer className="footer">
        <p>Built with Java, Spring Boot and React.</p>
      </footer>
    </>
  );
}
